package com.laonworks502.team1st.model.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class PostBean {
    private int no;
    private int board_id;
    private String title;
    private String writer;
    private String writerName;
    private String content;
    private Timestamp date;

}
