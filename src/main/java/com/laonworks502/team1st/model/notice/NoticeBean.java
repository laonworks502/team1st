package com.laonworks502.team1st.model.notice;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class NoticeBean {
    private int no;
    private String title;
    private String content;
    private Timestamp date;

}
