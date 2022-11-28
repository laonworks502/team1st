package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.UserBean;

public interface CommonUserService {

    // 로그인
    String loginUser(UserBean userBean);

    // 로그아웃
    String logoutUser();

}
