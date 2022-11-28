package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.General_UserBean;
import org.springframework.stereotype.Service;

@Service
public class General_UserServiceImpl implements General_UserService {

    @Override
    public String loginUser(String email, String passwd) {
        return null;
    }

    @Override
    public String logoutUser() {
        return null;
    }

    @Override
    public String joinCompany_User(General_UserBean general_userBean) {
        return null;
    }

    @Override
    public String amendCompany_User(General_UserBean general_userBean) {
        return null;
    }

    @Override
    public String findPasswdGeneral_User(General_UserBean general_userBean) {
        return null;
    }

    @Override
    public String quitCompany_User(String email, String exit_reason) {
        return null;
    }

}
 