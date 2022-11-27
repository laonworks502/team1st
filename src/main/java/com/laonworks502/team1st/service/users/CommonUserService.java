package com.laonworks502.team1st.service.users;

public interface CommonUserService {

    // 로그인
    public String loginUser(String email, String passwd);

    // 로그아웃
    public String logoutUser();

}
