package com.laonworks502.team1st.service.board;

import com.laonworks502.team1st.model.post.PostBean;

public interface BoardService {

    // 글 작성
    public String writePost(PostBean postBean) throws Exception;

    // 글 리스트 불러 오기
    public PostBean callBoardList(int board_id, int startPostNo, int endPostNo) throws Exception;

    // 글 상세 보기
    public PostBean callPostByNo(int no) throws Exception;

    // 글 수정
    public int amendPost(PostBean postBean) throws Exception;

    // 글 삭제
    public int deletePost(int no) throws Exception;

}
