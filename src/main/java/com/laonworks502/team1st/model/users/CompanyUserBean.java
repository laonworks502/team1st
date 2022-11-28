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
    private String te13;
    private Timestamp register_date;
    private String exit_reason;

}
