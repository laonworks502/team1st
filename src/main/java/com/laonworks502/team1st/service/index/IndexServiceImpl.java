package com.laonworks502.team1st.service.index;

import com.laonworks502.team1st.dao.index.IndexDao;
import com.laonworks502.team1st.model.board.BoardBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService{
    @Autowired
    private IndexDao indexDao;

    @Override
    public List<BoardBean> callAllBoards() throws Exception {
        return indexDao.callAllBoards();
    }
}
