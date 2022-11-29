package com.laonworks502.team1st.service.board;

import com.laonworks502.team1st.dao.board.BoardDao;
import com.laonworks502.team1st.model.post.PostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    @Override
    public String writePost(PostBean postBean) throws Exception{
        return boardDao.writePost(postBean);
    }

    @Override
    public PostBean callBoardList(int board_id, int startPostNo, int endPostNo) throws Exception {
        return boardDao.callBoardList(board_id, startPostNo, endPostNo);
    }

    @Override
    public PostBean callPostByNo(int no) throws Exception {
        return boardDao.callPostByNo(no);
    }

    @Override
    public String amendPost(PostBean postBean) throws Exception {
        return boardDao.amendPost(postBean);
    }

    @Override
    public String deletePost(int no) throws Exception {
        return boardDao.deletePost(no);
    }
}
