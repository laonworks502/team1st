package com.laonworks502.team1st.model.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int board_id;   // 게시판 번호
    private int page;       // 페이지 번호
    private int pagePostsCount;  // 페이지당 출력 수
    private int postsTotal;      // 게시판 전체 게시물수
    private int pagesTotal;      // 전체 페이지 수
    private int startPostNo;    // 페이지 게시물 처음 번호
    private int endPostNo;      // 페이지 게시물 마지막 번호

    final int PAGESCOUNT = 10;     // 페이징 페이지 크기
    private int startPage;      // 페이징 시작 페이지 번호
    private int endPage;        // 페이징 마지막 페이지 번호

    public Pagination(int startPage, int endPage) {
    }

    public Pagination(int board_id, int postsTotal, int pagePostsCount) {
        this.board_id = board_id;
        this.page = 1;
        this.postsTotal = postsTotal;
        this.pagePostsCount = pagePostsCount;
        pagesTotal = (postsTotal % pagePostsCount == 0) ? postsTotal / pagePostsCount : (postsTotal / pagePostsCount) + 1;
        startPostNo = postsTotal - ((page - 1) * pagePostsCount);
        endPostNo = (startPostNo > PAGESCOUNT) ? startPostNo - pagePostsCount + 1 : 1;
        startPage = ((page - 1) / PAGESCOUNT) * PAGESCOUNT + 1;
        endPage = (startPage + (PAGESCOUNT-1) > pagesTotal) ? pagesTotal : startPage + (PAGESCOUNT - 1);
    }

    public Pagination(int board_id, int page, int postsTotal, int pagePostsCount) {
        this.board_id = board_id;
        this.page = page;
        this.postsTotal = postsTotal;
        this.pagePostsCount = pagePostsCount;
        pagesTotal = (postsTotal % pagePostsCount == 0) ? postsTotal / pagePostsCount : (postsTotal / pagePostsCount) + 1;
        startPostNo = postsTotal - ((page - 1) * pagePostsCount);
        endPostNo = (startPostNo > 10) ? startPostNo - pagePostsCount + 1 : 1;
        startPage = ((page - 1) / PAGESCOUNT) * PAGESCOUNT + 1;
        endPage = (startPage + (PAGESCOUNT-1) > pagesTotal) ? pagesTotal : startPage + (PAGESCOUNT - 1);
    }

}
