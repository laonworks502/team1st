package com.laonworks502.team1st.service.scrap;

import com.laonworks502.team1st.dao.scrap.ScrapDao;
import com.laonworks502.team1st.model.scrap.ScrapBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScrapServiceImpl implements ScrapService{

    @Autowired
    private ScrapDao sd;

    /*[스크랩 검색]*/
    @Override
    public int searchScrap(ScrapBean scrap) throws Exception {
        return sd.searchScrap(scrap);
    }

    /*[스크랩 삭제]*/
    @Override
    public int deleteScrap(ScrapBean scrap) throws Exception {
        return  sd.deleteScrap(scrap);
    }

    /*[스크랩 생성]*/
    @Override
    public int insertScrap(Map scrap) throws Exception {
        return sd.insertScrap(scrap);
    }

    /*[스크랩 리스트)총 리스트 수]*/
    @Override
    public int getCount(String user_email) throws Exception {
        return sd.getCount(user_email);
    }

    /*[스크랩 전체 출력 리스트]*/
    @Override
    public List<ScrapBean> listTotalScrap(String user_email, int board_id, int listcount, int PAGES_COUNT) throws Exception {
        return sd.listTotalScrap(user_email, board_id, listcount, PAGES_COUNT);
    }

    /*[마이페이지) 미니 스크랩 리스트 ]*/
    @Override
    public List<ScrapBean> listMiniScrap(String user_email, int board_id, int listcount, int PAGES_COUNT) throws Exception {
        return sd.listMiniScrap(user_email, board_id, listcount, PAGES_COUNT);
    }


}
