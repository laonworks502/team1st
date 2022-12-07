package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CompanyUserBean extends UserBean{
    private String company_name;
    private String name;
    private String tel1;
    private String tel2;
    private String tel3;
    private Timestamp register_date;
    private String exit_reason;

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getPasswd() {
        return super.getPasswd();
    }

    @Override
    public void setPasswd(String passwd) {
        super.setPasswd(passwd);
    }
}
