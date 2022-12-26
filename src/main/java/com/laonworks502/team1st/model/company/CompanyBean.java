package com.laonworks502.team1st.model.company;

import lombok.Data;

import java.sql.Date;


@Data
public class CompanyBean {
    private String company_name;
    private String field;
    private String postal_code;
    private String address1;
    private String address2;
    private String tel1;
    private String tel2;
    private String tel3;
    private Date foundation_date;
}
