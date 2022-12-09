package com.laonworks502.team1st.service.scrap;

import com.laonworks502.team1st.model.scrap.ScrapBean;
import com.laonworks502.team1st.model.scrap.ScrapListBean;

import java.util.List;

public interface ScrapService {
    /*[스크랩 검색]*/
    public int searchScrap(ScrapBean scrap)throws Exception;

    /*[스크랩 삭제]*/
    public int deleteScrap(ScrapBean scrap)throws Exception;

    /*[스크랩 생성]*/
    public int insertScrap(ScrapBean scrap)throws Exception;

    /*[스크랩 리스트)총 리스트 수]*/
    public int getCount(String user_email)throws Exception;

    /*[스크랩 전체 리스트 출력 메소드]*/
    public List<ScrapListBean> listTotalScrap(String user_email, int board_id, int startPostNo, int PAGES_COUNT)throws Exception;

    /*[스크랩 미니 리스트 출력 메소드]*/
    public List<ScrapListBean> listMiniScrap(String user_email, int board_id)throws Exception;

    /*[전체 해당 페이지에 대한 검색]*/
    public int getBoardSearchList(String user_email, int no)throws Exception;

}
