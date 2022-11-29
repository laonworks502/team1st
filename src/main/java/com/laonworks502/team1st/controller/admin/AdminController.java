package com.laonworks502.team1st.controller.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.service.admin.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    //로그인 폼 뷰
    @RequestMapping(value = "adminlogin")
    public String adminlogin() throws Exception {
        return "adminlogin";
    }
//
//    @GetMapping(value = "/adminid/{adminid}")
//    public String getAdminId(@PathVariable("adminid") String var){
//        return var;
//    }

    // 로그인 인증
    @RequestMapping(value = "adminloginok", method = RequestMethod.POST)
    public String member_login_ok(@RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "passwd", required = false) String passwd,
                                  HttpSession session,
                                  Model model) throws Exception {
        int result = 0;
        AdminBean adm = adminService.userCheck(id);

        if (adm == null) {    // 등록되지 않은 회원일때

            result = 1;
            model.addAttribute("result", result);

            return "adminloginresult";

        } else {            // 등록된 회원일때
            if (adm.getPasswd().equals(passwd)) {// 비번이 같을때
                session.setAttribute("id", id);

                String adminid = adm.getId();
                String adminpasswd = adm.getPasswd();

                model.addAttribute("adminid", adminid);
                model.addAttribute("adminpw", adminpasswd);

                return "adminmain";

            } else {// 비번이 다를때
                result = 2;
                model.addAttribute("result", result);

                return "adminloginresult";
            }
        }

    }
}


