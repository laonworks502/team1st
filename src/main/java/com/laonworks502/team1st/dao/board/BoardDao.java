package com.laonworks502.team1st.dao.board;

import com.laonworks502.team1st.model.post.PostBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDao {

    int writePost(PostBean postBean) throws Exception;
    List<PostBean> callBoardList(/*@Param("board_id")*/ int board_id,
                                 /*@Param("startPostNo")*/ int startPostNo,
                                 /*@Param("PAGES_COUNT")*/ int PAGES_COUNT) throws Exception;
    int countAllPosts(int board_id) throws Exception;
    String getBoardNameById(int board_id) throws Exception;
    PostBean callPostByNo(int board_id, int no) throws Exception;
    int amendPost(PostBean postBean) throws Exception;
    int deletePost(int no) throws Exception;
}
