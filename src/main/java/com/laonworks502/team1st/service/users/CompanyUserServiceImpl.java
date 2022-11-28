package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import org.springframework.stereotype.Service;

@Service
public class CompanyUserServiceImpl implements CompanyUserService {

    @Override
    public String loginUser(UserBean userBean) {
        return null;
    }

    @Override
    public String logoutUser() {
        return null;
    }

    @Override
    public String joinCompany_User(CompanyUserBean company_userBean) {
        return null;
    }

    @Override
    public String amendCompany_User(CompanyUserBean company_userBean) {
        return null;
    }

    @Override
    public String findPasswdCompany_User(CompanyUserBean company_userBean) {
        return null;
    }

    @Override
    public String quitCompany_User(String email, String exit_reason) {
        return null;
    }
}
