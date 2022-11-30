package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.UserBean;

public interface CommonUserService {

    // 로그인
    String loginUser(UserBean userBean);

    // 로그아웃
    String logoutUser(UserBean userBean);

    // 기업 회원 가입
    int joinUser(UserBean userBean) throws Exception;

    // 기업 회원 수정
    String amendUser(UserBean userBean);

    // 기업 회원 비밀번호 찾기
    String findPasswdUser(UserBean userBean);

    // 기업 회원 탈퇴
    String quitUser(UserBean userBean);


}
