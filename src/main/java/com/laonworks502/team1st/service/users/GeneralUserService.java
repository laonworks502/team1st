package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.GeneralUserBean;

public interface GeneralUserService extends CommonUserService {

    // 일반 회원 가입
    String joinCompany_User(GeneralUserBean general_userBean);

    // 일반 회원 수정
    String amendCompany_User(GeneralUserBean general_userBean);

    // 기업 회원 비밀번호 찾기
    String findPasswdGeneral_User(GeneralUserBean general_userBean);

    // 일반 회원 탈퇴
    String quitCompany_User(String email, String exit_reason);
}
