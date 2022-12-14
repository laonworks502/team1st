package com.laonworks502.team1st.model.board;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ApplicantBean {
    private int no;
    private String email;
    private String name;
    private String resume;
    private Timestamp date;
}
