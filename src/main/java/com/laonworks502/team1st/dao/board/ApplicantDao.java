package com.laonworks502.team1st.dao.board;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicantDao {

    // 지원 insert
    int insertApply(int no, String email);

    // 지원 유무 select
    int searchApply(int no, String email);
}
