package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.dao.users.CheckApplyDao;
import com.laonworks502.team1st.model.applicant.ApplicantBean;
import com.laonworks502.team1st.model.users.JobPostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CheckApplyService")
public class CheckApplyServiceImpl implements CheckApplyService{

    @Autowired
    private CheckApplyDao checkApplyDao;

    // 구인글 목록
    @Override
    public List<JobPostBean> getJobPosts(String email) throws Exception {
        return checkApplyDao.getJobPosts(email);
    }

    // 지원자 목록 보기
    @Override
    public List<ApplicantBean> getApplicants(int no) throws Exception {
        return checkApplyDao.getApplicants(no);
    }

    // 이력서 다운로드
    @Override
    public String getResume(String email) throws Exception {
        return checkApplyDao.getResume(email);
    }

    // 지원 내역 목록 보기
    @Override
    public List<ApplicantBean> getMyApply(String email) throws Exception {
        System.out.println("service emailcheck:"+email);
        return checkApplyDao.getMyApply(email);
    }

    // 지원 취소
    @Override
    public void cancelMyApply(String email, int no) throws Exception {
        checkApplyDao.cancelMyApply(email, no);
    }
}
