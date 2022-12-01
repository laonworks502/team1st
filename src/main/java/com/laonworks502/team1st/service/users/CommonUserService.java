package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.UserBean;

public interface CommonUserService {

    // 로그인
    String loginUser(UserBean userBean) throws Exception;

    // 로그아웃
    String logoutUser(UserBean userBean) throws Exception;

    // 회원 가입
    int joinUser(UserBean userBean) throws Exception;

    // 회원 수정
    int amendUser(UserBean userBean) throws Exception;

    // 회원 탈퇴
    int quitUser(UserBean userBean) throws Exception;

}