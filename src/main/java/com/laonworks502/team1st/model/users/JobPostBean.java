package com.laonworks502.team1st.model.users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Data
public class JobPostBean {
    private int no;
    private int board_id;
    private String title;
    private String writer;
    private Timestamp date;
    private int applicants;

}