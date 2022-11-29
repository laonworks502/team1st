package com.laonworks502.team1st.model.admin;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("admin")
public class AdminBean {
    private String id;
    private String passwd;

}
