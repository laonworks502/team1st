package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.applicant.ApplicantBean;

import java.util.List;

public interface CheckApplyService {
    // 지원자 목록 보기
    List<ApplicantBean> getApplicants(int no) throws Exception;

    // 이력서 다운로드
    String getResume(String email) throws Exception;

    // 지원 내역 목록 보기
    List<ApplicantBean> getMyApply(String email) throws Exception;

    // 지원 취소
    void cancelMyApply(String email, int no) throws Exception;

}
