package com.laonworks502.team1st.model.reports;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportsBean {
    private int no;
    private String reporter_email;
    private String board_id;
    private String post_no;
    private String content;
    private Date date_reported;

}
