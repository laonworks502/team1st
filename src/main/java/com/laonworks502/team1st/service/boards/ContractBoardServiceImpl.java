package com.laonworks502.team1st.service.boards;

import com.laonworks502.team1st.model.post.PostBean;
import org.springframework.stereotype.Service;

@Service
public class ContractBoardServiceImpl implements BoardService {
    @Override
    public String writePost(PostBean postbean) {
        return null;
    }

    @Override
    public PostBean callBoardList(int board_id, int startPage, int endPage) {
        return null;
    }

    @Override
    public PostBean callPostByNo(int no) {
        return null;
    }

    @Override
    public String amendPost(PostBean postbean) {
        return null;
    }

    @Override
    public String deletePost(int no) {
        return null;
    }
}
