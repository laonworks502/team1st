package com.laonworks502.team1st.model.users;

abstract class UserBean {
    protected String email;
    protected String passwd;

    abstract String getEmail();
    abstract void setEmail(String email);
    abstract String getPasswd();
    abstract void setPasswd(String passwd);

}
