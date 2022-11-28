package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.UserBean;

public interface CommonUserService {

    // 로그인
    String loginUser(UserBean userBean);

    // 로그아웃
    String logoutUser();

    // 기업 회원 가입
    String joinCompany_User(UserBean userBean);

    // 기업 회원 수정
    String amendCompany_User(UserBean userBean);

    // 기업 회원 비밀번호 찾기
    String findPasswdCompany_User(UserBean userBean);

    // 기업 회원 탈퇴
    String quitCompany_User(UserBean userBean);


}
