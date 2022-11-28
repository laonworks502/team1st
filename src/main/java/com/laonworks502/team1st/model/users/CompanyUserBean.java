package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyUserBean extends UserBean{
    private String company_name;
    private String name;
    private String tel1;
    private String tel2;
    private String te13;
    private Date register_date;
    private String exit_reason;

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }

    @Override
    public void setPasswd(String passwd) {
        this.passwd=passwd;
    }
}
