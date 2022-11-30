package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.model.board.Pagination;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.service.board.BoardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    private BoardServiceImpl bs;

    // 글 작성
    @PostMapping(value = "/postwrite/{board_id}")
    public String writePost(
            @PathVariable(value = "board_id") int board_id,
            HttpServletRequest request,
            HttpSession session) throws Exception {

        // PostBean 생성
        PostBean post = new PostBean();
        post.setBoard_id(board_id);
        post.setTitle((String)request.getAttribute("title"));
        post.setWriter((String)request.getAttribute("writer"));
        post.setContent((String)request.getAttribute("content"));

        // 리다이렉트 할 페이지
        int page = (int)request.getAttribute("page");
        int no = bs.writePost(post);

        String boardName = bs.getBoardNameById(board_id);

        return "redirect:/" + boardName + "/" + page + "/" + no;
    }

    // 글 목록
    @GetMapping(value = {"/board/{board_id}", "/board/{board_id}/{page}"})
    public String callBoardList(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page",required = false) Integer page, Model model) throws Exception {
        if (page != null) page = 1;

        int postTotal = bs.countAllPosts(board_id);

        Pagination pg = new Pagination(board_id, postTotal, 10);
        model.addAttribute("pg", pg);

        List<PostBean> postList = bs.callBoardList(board_id, pg.getStartPostNo(), pg.getEndPostNo());
        model.addAttribute("postList", postList);

        String boardName = bs.getBoardNameById(board_id);

        return boardName + "/" + page;
    }

    // 글 상세보기
    @GetMapping(value = "/{boardName}/{page}/{no}")
    public String callPostByNo(@PathVariable(value = "boardName") String boardName,
                               @PathVariable(value = "page") int page,
                               @PathVariable(value = "no") int no){

        return null;
    }

    // 글 수정
    @PutMapping(value = "")
    public String amendPost(){
        return null;
    }

    // 글 삭제
    @DeleteMapping(value = "")
    public String deletePost(){
        return null;
    }

}
