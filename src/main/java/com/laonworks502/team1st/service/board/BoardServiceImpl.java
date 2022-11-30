package com.laonworks502.team1st.service.board;

import com.laonworks502.team1st.dao.board.BoardDao;
import com.laonworks502.team1st.model.post.PostBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    @Override
    public int writePost(PostBean postBean) throws Exception{
        return boardDao.writePost(postBean);
    }

    @Override
    public List<PostBean> callBoardList(int board_id, int startPostNo, int PAGES_COUNT) throws Exception {
        return boardDao.callBoardList(board_id, startPostNo, PAGES_COUNT);
    }

    @Override
    public String getBoardNameById(int board_id) throws Exception {
        return boardDao.getBoardNameById(board_id);
    }

    @Override
    public int countAllPosts(int board_id) throws Exception {
        return boardDao.countAllPosts(board_id);
    }

    @Override
    public PostBean callPostByNo(int board_id, int no) throws Exception {
        return boardDao.callPostByNo(board_id, no);
    }

    @Override
    public int amendPost(PostBean postBean) throws Exception {
        return boardDao.amendPost(postBean);
    }

    @Override
    public int deletePost(int no) throws Exception {
        return boardDao.deletePost(no);
    }
}
