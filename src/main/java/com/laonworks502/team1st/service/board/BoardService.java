package com.laonworks502.team1st.service.board;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.post.PostListBean;

import java.util.List;

public interface BoardService {

    // 있는 게시판인지 체크
    int checkBoardExist(int board_id) throws Exception;

    // 글 작성
    int writePost(PostBean postBean) throws Exception;

    // 글 리스트 불러 오기
    List<PostListBean> getBoardList(int board_id, int startPostNo, int PAGES_COUNT) throws Exception;

    // 게시물 전체 개수
    int countAllPosts(int board_id) throws Exception;

    // 게시판 id 에서 boardbean 추출
    BoardBean getBoardById(int board_id) throws Exception;

    // 게시판 id 에서 이름 추출
    String getBoardNameById(int board_id) throws Exception;

    // 글 상세 보기
    PostBean getPostByNo(int board_id, int no) throws Exception;

    // 글 수정
    int amendPost(PostBean postBean) throws Exception;

    // 글 삭제
    int deletePost(int no) throws Exception;


}
