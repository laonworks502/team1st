package com.laonworks502.team1st.dao.board;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.post.PostListBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    int checkBoardExist(int board_id) throws Exception;
    int writePost(PostBean postBean) throws Exception;
    List<PostListBean> getBoardList(/*@Param("board_id")*/ int board_id,
                                 /*@Param("startPostNo")*/ int startPostNo,
                                 /*@Param("PAGES_COUNT")*/ int PAGES_COUNT) throws Exception;
    int countAllPosts(int board_id) throws Exception;
    BoardBean getBoardById(int board_id) throws Exception;
    String getBoardNameById(int board_id) throws Exception;
    PostBean getPostByNo(int board_id, int no) throws Exception;
    int amendPost(PostBean postBean) throws Exception;
    int deletePost(int no) throws Exception;
}
