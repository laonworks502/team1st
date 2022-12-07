package com.laonworks502.team1st.dao.scrap;

import com.laonworks502.team1st.model.scrap.ScrapBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScrapDao {

    /*[스크랩 검색]*/
    public int searchScrap(ScrapBean scrap) throws Exception;

    /*[스크랩 삭제]*/
    public int deleteScrap(ScrapBean scrap) throws Exception;

    /*[스크랩 생성]*/
    public int insertScrap(Map scrap) throws Exception;

    /*[스크랩 리스트)총 리스트 수]*/
    public int getCount(String user_email) throws Exception;

    /*[스크랩 리스트 출력 메소드]*/
    List<ScrapBean> listScrap(String user_email, int board_id, int startPostNo, int PAGES_COUNT) throws Exception;

}