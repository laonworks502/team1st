package com.laonworks502.team1st.service.boards;

import com.laonworks502.team1st.model.post.PostBean;

public interface BoardService {

    // 글 작성
    public String writePost(PostBean postbean);

    // 글 리스트 불러 오기
    public PostBean callBoardList(int board_id, int startPage, int endPage);

    // 글 상세 보기
    public PostBean callPostByNo(int no);

    // 글 수정
    public String amendPost(PostBean postbean);

    // 글 삭제
    public String deletePost(int no);

}
