package com.laonworks502.team1st.model.users;

public class UserBean extends User{
    @Override
    String getEmail() {
        return email;
    }

    @Override
    void setEmail(String email) {
        this.email=email;
    }

    @Override
    String getPasswd() {
        return passwd;
    }

    @Override
    void setPasswd(String passwd) {
        this.passwd=passwd;
    }
}
