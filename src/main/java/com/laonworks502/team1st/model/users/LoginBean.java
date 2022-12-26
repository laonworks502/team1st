package com.laonworks502.team1st.model.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBean {
    private String email;
    private String authority;	// 기업, 일반
    
    public LoginBean(String email, String authority) {
    	this.email=email;
    	this.authority=authority;
    }
}
