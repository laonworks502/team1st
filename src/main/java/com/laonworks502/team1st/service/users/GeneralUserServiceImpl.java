package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import org.springframework.stereotype.Service;

@Service
public class GeneralUserServiceImpl implements CommonUserService {

    @Override
    public String loginUser(UserBean userBean) {
        return null;
    }

    @Override
    public String logoutUser() {
        return null;
    }

    @Override
    public String joinCompany_User(UserBean userBean) {
        return null;
    }

    @Override
    public String amendCompany_User(UserBean userBean) {
        return null;
    }

    @Override
    public String findPasswdCompany_User(UserBean userBean) {
        return null;
    }

    @Override
    public String quitCompany_User(UserBean userBean) {
        return null;
    }
}
