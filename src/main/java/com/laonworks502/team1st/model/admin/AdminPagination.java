package com.laonworks502.team1st.model.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AdminPagination {
    private int page;       // 페이지 번호
    private final int PAGES_COUNT = 10;     // 페이징 페이지 크기
    private int pagesTotal;      // 전체 페이지 수
    private int startPage;      // 페이징 시작 페이지 번호
    private int endPage;        // 페이징 마지막 페이지 번호

    public AdminPagination(int startPage, int endPage) {
    }

    public AdminPagination(int board_id, int usersTotal, int pagePostsCount) {
        this.page = 1;
        pagesTotal = (usersTotal % pagePostsCount == 0) ?
                usersTotal / pagePostsCount : (usersTotal / pagePostsCount) + 1;
        startPage = ((page - 1) / PAGES_COUNT) * PAGES_COUNT + 1;
        endPage = (startPage + (PAGES_COUNT-1) > pagesTotal) ? pagesTotal : startPage + (PAGES_COUNT - 1);
    }

    public AdminPagination(int board_id, int page, int usersTotal, int pagePostsCount) {
        this.page = page;
        pagesTotal = (usersTotal % pagePostsCount == 0) ?
                usersTotal / pagePostsCount : (usersTotal / pagePostsCount) + 1;
        startPage = ((page - 1) / PAGES_COUNT) * PAGES_COUNT + 1;
        endPage = (startPage + (PAGES_COUNT-1) > pagesTotal) ? pagesTotal : startPage + (PAGES_COUNT - 1);
    }

}
