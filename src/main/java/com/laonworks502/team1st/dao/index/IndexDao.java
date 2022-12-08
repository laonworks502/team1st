package com.laonworks502.team1st.dao.index;

import com.laonworks502.team1st.model.board.BoardBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexDao {
    List<BoardBean> callAllBoards() throws Exception;

}
