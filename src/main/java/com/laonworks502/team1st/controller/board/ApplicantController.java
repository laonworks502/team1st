package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.board.ApplicantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping(value = "/apply")
public class ApplicantController {

    @Autowired
    @Qualifier("ApplicantService")
    private ApplicantService applicantService;

    @ResponseBody
    @PostMapping(value = "/{no}")
    public Integer insertApply(@PathVariable(value = "no") int no,
                               HttpSession session) throws Exception {

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        int result = applicantService.insertApply(no, email);

        return result;
    }

    @ResponseBody
    @GetMapping(value = "/{no}")
    public Integer selectApply(@PathVariable(value = "no") int no,
                               HttpSession session) throws Exception {

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        int result = applicantService.searchApply(no, email);

        return result;
    }


}
