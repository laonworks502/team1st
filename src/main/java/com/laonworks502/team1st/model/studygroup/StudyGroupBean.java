package com.laonworks502.team1st.model.studygroup;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class StudyGroupBean {
    private int no;
    private String host_email;
    private int total_members;
    private String members_email;
    private Timestamp date;
    private Date deadline;
}
