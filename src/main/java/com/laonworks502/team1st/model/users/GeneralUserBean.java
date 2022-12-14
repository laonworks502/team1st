package com.laonworks502.team1st.model.users;

import lombok.Data;

import java.sql.Date;


@Data
public class GeneralUserBean extends UserBean{
    private String name;
    private String postal_code;
    private String address1;
    private String address2;
    private String tel1;
    private String tel2;
    private String tel3;
    private Date register_date;
    private String resume;
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
