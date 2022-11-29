package com.laonworks502.team1st.model.post;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PostBean {
    private int no;
    private String board_id;
    private String title;
    private String writer;
    private String content;
    private Timestamp date;

}
