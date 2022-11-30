package com.laonworks502.team1st.dao.board;

import com.laonworks502.team1st.model.post.PostBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
    String writePost(PostBean postBean) throws Exception;
    PostBean callBoardList(int board_id, int startPage, int endPage) throws Exception;
    PostBean callPostByNo(int no) throws Exception;
    int amendPost(PostBean postBean) throws Exception;
    int deletePost(int no) throws Exception;
}
