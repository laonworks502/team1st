package com.laonworks502.team1st.model.scrap;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ScrapBean {
    private String user_email;
    private int no;
    private Timestamp date;
}
