package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.General_UserBean;

public interface General_UserService extends CommonUserService {

    // 일반 회원 가입
    public String joinCompany_User(General_UserBean general_userBean);

    // 일반 회원 수정
    public String amendCompany_User(General_UserBean general_userBean);

    // 기업 회원 비밀번호 찾기
    public String findPasswdGeneral_User(General_UserBean general_userBean);

    // 일반 회원 탈퇴
    public String quitCompany_User(String email, String exit_reason);
}
