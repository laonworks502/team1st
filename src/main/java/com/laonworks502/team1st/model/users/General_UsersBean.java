package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class General_UsersBean {
    private String email;
    private String passwd;
    private String name;
    private String post;
    private String address1;
    private String address2;
    private String tel1;
    private String tel2;
    private String te13;
    private Date register_date;
    private String exit_reason;
    private String resume;

}
