package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.service.board.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/resume")
public class ApplicantController {

    @Autowired
    @Qualifier("ApplicantService")
    private ApplicantService applicantService;
}
