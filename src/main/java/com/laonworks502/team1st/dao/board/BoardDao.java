package com.laonworks502.team1st.dao.board;

import com.laonworks502.team1st.model.post.PostBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
    int writePost(PostBean postBean) throws Exception;
    List<PostBean> callBoardList(int board_id, int startPage, int endPage) throws Exception;
    int countAllPosts(int board_id) throws Exception;
    String getBoardNameById(int board_id) throws Exception;
    PostBean callPostByNo(int no) throws Exception;
    String amendPost(PostBean postBean) throws Exception;
    String deletePost(int no) throws Exception;
}
