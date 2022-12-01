package com.laonworks502.team1st.controller.users;

import ch.qos.logback.classic.Logger;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import com.laonworks502.team1st.service.users.GeneralUserServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class GeneralUserController {

    @Autowired
    private GeneralUserServiceImpl gus;

    // 로그인 폼
    @RequestMapping("loginForm")
    public String generaluserloginForm() throws Exception{

        return "generaluser/loginForm";
    }

    // 로그인 실행
    @RequestMapping("generaluserlogin_ok")
    public String generaluserlogin_ok(GeneralUserBean gub,
                                      HttpSession session,
                                      Model model,
                                      @RequestParam("email") String email,
                                      @RequestParam("passwd") String passwd) throws Exception{

        int result = 0;

        gub = gus.checkGeneraluser(email);

        if(gub == null){    // 등록되지 않은 회원

            result = 1;
            model.addAttribute("result", result);

            return "generaluser/loginResult";

        }else{              // 등록 회원 확인됨
            if(gub.getPasswd().equals(passwd)) {        // 비번 같아서 로그인됨
                session.setAttribute("email", email);
                System.out.println("로그인성공");

                return "generaluser/loginsuccess";
            }else{                                      // 비번 달라서 로그인 안됨
                result = 2;
                model.addAttribute("result", result);

                return "generaluser/loginResult";
            }
        }
    }

    // 로그아웃
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();

        System.out.println("로그아웃");
        return "generaluser/loginForm";
    }

    // 회원가입 폼
    @RequestMapping(value = "/generaluserinsert")
    public String generaluserinsert(GeneralUserBean gub) throws  Exception{

        return "generaluser/generaluserinsert";
    }

    // 회원가입 실행
    @RequestMapping("generaluserinsert_ok")
    public String generaluserinsert_ok(GeneralUserBean gub,
                                       Model model) throws Exception {

        gus.joinUser(gub);

        log.info("companyuserinsert_ok!");    // 뷰에 에러뜸 회원가입 값은 넘어감

        return "generaluser/loginForm"; // 가입 후 로그인페이지로 이동
    }

    // 이메일중복체크
    @PostMapping("/emailcheck")
    @ResponseBody
    public int emailcheck(@RequestParam("email") String email) throws Exception{

        int cnt = gus.emailDuplicatecheck(email);

        return cnt;
    }

    // 회원수정 폼
    @RequestMapping("generaluseredit")
    public String generaluseredit(HttpSession session, Model model) throws Exception {

        String email = (String)session.getAttribute("email");

        GeneralUserBean gub = gus.checkGeneraluser(email);

        model.addAttribute("gub",gub);

        log.info("회원수정폼에서 회원정보 불러오기");

       return "generaluser/generaluseredit";
    }

    // 회원수정 실행
    @RequestMapping("generaluseredit_ok")
    public String generaluseredit_ok(HttpSession session,
                                     HttpServletRequest request,
                                     GeneralUserBean gub) throws Exception {

        GeneralUserBean old = gus.checkGeneraluser(gub.getEmail());

//        if(gub.getPasswd().equals(old.getPasswd())) {
//            gus.updateGeneraluser(gub);
//            System.out.println("진입확인===============");
//            return "redirect:/generaluser/loginsuccess";
//        }
        int result = gus.updateGeneraluser(gub);
        if(result == 1) log.info("수정 성공");

        return "generaluser/loginsuccess";
    }

    // 회원삭제 폼
    @RequestMapping("generaluserdelete")
    public String generaluserdelete(HttpSession session,
                                    GeneralUserBean gub,
                                    Model model) throws Exception {

        String email = (String)session.getAttribute("email");

        gub = gus.checkGeneraluser(email);

        model.addAttribute("gub",gub);

        log.info("회원삭제폼");

        return "generaluser/generaluserdelete";
    }

    // 회원삭제 실행
    @RequestMapping("generaluserdelete_ok")
    public String generaluserdelete_ok(@RequestParam("exit_reason") String exit_reason,
                                       HttpSession session,
                                       HttpServletRequest request,
                                       GeneralUserBean gub) throws Exception {

        String email = (String)session.getAttribute("email");
        gub.setExit_reason(exit_reason);
        int result = gus.deleteGeneraluser(gub);
        if(result == 1) log.info("탈퇴사유 : " + gub.getExit_reason());

        session.invalidate();

        return "generaluser/loginForm";
    }
}
