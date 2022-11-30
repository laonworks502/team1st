package com.laonworks502.team1st.controller.users;

import ch.qos.logback.classic.Logger;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import com.laonworks502.team1st.service.users.GeneralUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class GeneralUserController {

    @Autowired
    private GeneralUserServiceImpl gus;

    @RequestMapping(value = "/generaluserinsert")
    public String generaluserinsert(GeneralUserBean gub) throws  Exception{

        return "generaluser/generaluserinsert";
    }

    @RequestMapping("generaluserinsert_ok")
    public String generaluserinsert_ok(GeneralUserBean gub,
                                       Model model) throws Exception {

        gus.joinUser(gub);
        Logger log = null;
//        log.info("companyuserinsert_ok!");    // 뷰에 에러뜸 회원가입 값은 넘어감
        return "generaluser/generaluserlogin"; // 가입 후 로그인페이지로 이동
    }

    @PostMapping("/emailcheck")
    @ResponseBody
    public int emailcheck(@RequestParam("email") String email) throws Exception{

        int cnt = gus.emailDuplicatecheck(email);

        return cnt;
    }


}
