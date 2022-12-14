package com.laonworks502.team1st.service.board;

import com.laonworks502.team1st.dao.board.ApplicantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ApplicantService")
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantDao applicantDao;

    @Override
    public int insertApply(int no, String email) {
        return applicantDao.insertApply(no, email);
    }

    @Override
    public int searchApply(int no, String email) {
        return applicantDao.searchApply(no, email);
    }
}
