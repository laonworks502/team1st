package com.laonworks502.team1st.model.notice;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NoticeBean {
    private int no;
    private String title;
    private String content;
    private Date date_noticed;

}
