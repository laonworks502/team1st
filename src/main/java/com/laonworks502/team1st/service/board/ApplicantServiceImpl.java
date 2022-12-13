package com.laonworks502.team1st.service.board;

import com.laonworks502.team1st.dao.board.ApplicantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ApplicantService")
public class ApplicantServiceImpl {

    @Autowired
    private ApplicantDao applicantDao;

}
