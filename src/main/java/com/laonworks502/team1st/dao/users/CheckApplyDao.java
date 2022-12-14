package com.laonworks502.team1st.dao.users;

import com.laonworks502.team1st.model.applicant.ApplicantBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CheckApplyDao {
    // 지원자 목록
    List<ApplicantBean> getApplicants(int no) throws Exception;

    // 이력서 다운로드
    String getResume(String email) throws Exception;

    // 지원내역 목록
    List<ApplicantBean> getMyApply(String email) throws Exception;

    // 지원 취소
    String cancelMyApply(@Param("email") String email, @Param("no") int no) throws Exception;
}
