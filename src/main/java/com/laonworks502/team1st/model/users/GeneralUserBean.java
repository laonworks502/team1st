package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GeneralUserBean extends UserBean{
    private String name;
    private String post;
    private String address1;
    private String address2;
    private String tel1;
    private String tel2;
    private String te13;
    private Date register_date;
    private String resume;
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
