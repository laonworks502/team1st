package com.laonworks502.team1st.model.board;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ApplicantBean {
    private int no;
    private String applicant;
    private Timestamp date;
}
