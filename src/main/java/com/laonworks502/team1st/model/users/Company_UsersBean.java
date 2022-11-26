package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Company_UsersBean {
    private String email;
    private String passwd;
    private String company_name;
    private String tel1;
    private String tel2;
    private String te13;
    private Date register_date;

}
