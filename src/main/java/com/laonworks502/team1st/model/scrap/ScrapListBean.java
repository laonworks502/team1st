package com.laonworks502.team1st.model.scrap;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class ScrapListBean {
    private int no;
    private int board_id;
    private String title;
    private String user_email;
    private String content;
    private Timestamp date;
}
