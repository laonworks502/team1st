package com.laonworks502.team1st.service.board;

public interface ApplicantService {

    // 지원 insert
    int insertApply(int no, String email);

    // 지원 유무 select
    int searchApply(int no, String email);
}
