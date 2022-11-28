package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class GeneralUserBean extends UserBean{
    private String name;
    private String postal_code;
    private String address1;
    private String address2;
    private String tel1;
    private String tel2;
    private String te13;
    private Timestamp register_date;
    private String resume;
    private String exit_reason;

}
