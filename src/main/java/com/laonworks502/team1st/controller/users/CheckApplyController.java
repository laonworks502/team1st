package com.laonworks502.team1st.controller.users;

import com.laonworks502.team1st.model.applicant.ApplicantBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.users.CheckApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/apply")
public class CheckApplyController {

    @Autowired
    @Qualifier("CheckApplyService")
    private CheckApplyService checkApplyService;

    // 지원자 목록 보기
    @GetMapping(value = "/applicants")
    public ModelAndView getApplicants(@RequestParam(value = "no") int no) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/companyuser/applicants-list");

        List<ApplicantBean> applicants = checkApplyService.getApplicants(no);
        modelAndView.addObject("applicants", applicants);

        return modelAndView;
    }

    // 이력서 다운로드
    @ResponseBody
    @GetMapping(value = "/resume")
    public String getResume(@RequestBody Map<String, String> emailMap) throws Exception {

        String email = emailMap.get("email");

        String resume = checkApplyService.getResume(email);

        // 다운로드 처리

        return "";
    }

    // 지원 내역 목록 보기
    @GetMapping(value = "/applies")
    public ModelAndView getMyApply(HttpSession session) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/generaluser/applies-list");

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");

        String email = loginBean.getEmail();
        log.info("emailcheck={}",email);
        List<ApplicantBean> applies = checkApplyService.getMyApply(email);
        for (ApplicantBean a : applies){
            log.info("list {}", a);
            log.info("list {}", a.getNo());
            log.info("list {}", a.getDate());

        }

        modelAndView.addObject("applies", applies);

        return modelAndView;
    }

    // 지원 취소
    @GetMapping(value = "/applies/{no}")
    public ModelAndView cancelMyApply(@PathVariable(value = "no") Integer no, HttpSession session) throws Exception{
        ModelAndView modelAndView = new ModelAndView("redirect:/apply/applies");

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");

        String email = loginBean.getEmail();
        checkApplyService.cancelMyApply(email, no);

        return modelAndView;
    }

}
