package com.laonworks502.team1st.controller.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AdminController<model> {

    @Autowired
    AdminService adminService;
    //관리자 로그인 폼 진입
    @RequestMapping(value="adminloginform")
    public String adiminloginform() throws Exception {
        log.info("*****관리자 로그인 폼 진입*****");

        return "admin/adminloginform";
    }

    // 관리자 로그인 하기 
    @RequestMapping(value = "adminlogin", method = RequestMethod.POST)
    public String adminlogin(@RequestParam String adminId, @RequestParam String adminPw, HttpSession session,
                                Model model) throws Exception {

        log.info("관리자 로그인 실행 메소드 진입");

        //관리자 계정 정보 가져오기
        AdminBean adminBean = adminService.getAdminInfo(adminId);
        adminService.getAdminInfo(adminPw);
        
        //폼에서 입력한 계정 정보와 DB에서 가져온 계정 정보 비교
        int result = 0;

        if (adminBean.equals(adminId) && adminBean.equals(adminPw)) {    // 관리자 아이디 && 비밀번호 일치

                log.info("관리자 로그인 성공");
                    
                session.setAttribute("adminId", adminId);
                
                log.info("관리자 계정정보 세션 공유 성공");
                        
                model.addAttribute("admeanBean" , adminBean);

                return "admin/adminmain";

            } else {                                                // 관리자 아이디 ||비밀번호 불일치

                log.info("관리자 로그인 실패");
                result = 1;
                model.addAttribute("result", result);

                return "adminloginfail";
            }
        } // if - else end
    }




//package com.laonworks502.team1st.controller.admin;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class AdminController {
//
////    @Autowired
////    private AdminServiceImpl adminService;
//
////    @RequestMapping(value = "adminlogin", method = RequestMethod.GET)
////            public String adminLogin() {
////        return "admin/adminlogin";
////    }
//
////    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
////    public String adminmain(Model model, @RequestParam String adminId, @RequestParam String adminPw) {
////        if(adminId.equals("admin") && adminPw.equals("admin502")){
////            model.addAttribute("adminId", adminId);
////            return "adminloginmain";
////        }
//////        model.put("errorMessage", "아이디나 비밀번호를 확인하세요.");
////        return "admin/adminlogin";
////    }
//////
//
//
//    //로그인 폼 뷰
//    @RequestMapping(value = "adminlogin")
//    public String adminlogin() throws Exception {
//        return "admin/adminlogin";
//    }
//
//    // 로그인 인증
//    @RequestMapping(value = "adminloginok", method = RequestMethod.POST)
//    public String member_login_ok(@RequestParam(value = "id", required = false) String id,
//                                  HttpSession session,
//                                  Model model) throws Exception {
//        int result = 0;
//        AdminBean adm = adminService.userCheck(id);
//
//        if (adm == null) {    // 등록되지 않은 회원일때
//
//            result = 1;
//            model.addAttribute("result", result);
//
//            return "admin/adminloginresult";
//
//        } else {            // 등록된 회원일때
//            if (adm.getPasswd().equals(passwd)) {// 비번이 같을때
//                session.setAttribute("id", id);
//
//                String adminid = adm.getId();
//                String adminpasswd = adm.getPasswd();
//
//                model.addAttribute("adminid", adminid);
//                model.addAttribute("adminpw", adminpasswd);
//
//                return "admin/adminmain";
//
//            } else {// 비번이 다를때
//                result = 2;
//                model.addAttribute("result", result);
//
//                return "admin/adminloginresult";
//            }
//        }
//
//    }
//}
//
//
