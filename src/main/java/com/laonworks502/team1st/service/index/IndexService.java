package com.laonworks502.team1st.service.index;

import com.laonworks502.team1st.dao.studygroup.board.BoardBean;

import java.util.List;

public interface IndexService {
    List<BoardBean> callAllBoards() throws Exception;
}
