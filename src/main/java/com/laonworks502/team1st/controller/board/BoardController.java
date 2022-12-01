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

    // 글 작성 폼
    @GetMapping(value = "/postwriteform/{board_id}/{page}")
    public String postwriteform(
            @PathVariable("board_id") int board_id,
            @PathVariable("page") int page,
            Model model){

        model.addAttribute("board_id",board_id);
        model.addAttribute("page",page);

        return "board/postwrite";
    }

    // 글 작성
    @PostMapping(value = "/postwrite/{board_id}/{page}")
    public String writePost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @ModelAttribute PostBean post,
            HttpSession session) throws Exception {

        // PostBean 생성
        post.setBoard_id(board_id);

        int no = bs.writePost(post);

        String boardName = bs.getBoardNameById(board_id);

        return "redirect:/" + boardName + "/" + page + "/" + no;
    }

    // 글 목록
    @GetMapping(value = {"/boards/{board_id}", "/boards/{board_id}/{page}"})
    public String callBoardList(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page",required = false) Integer page, Model model) throws Exception {

        if (page == null) page = 1;

        int postTotal = bs.countAllPosts(board_id);

        Pagination pg = new Pagination(board_id, postTotal, 10);
        model.addAttribute("pg", pg);

        List<PostBean> postList = bs.callBoardList(board_id, pg.getStartPostNo(), pg.getPAGES_COUNT());
        model.addAttribute("postList", postList);
        model.addAttribute("board_id",board_id);
        model.addAttribute("page",page);

        String boardName = bs.getBoardNameById(board_id);

        model.addAttribute("boardName", boardName);

        return "board/boardlist";
    }

    // 글 상세보기
    @GetMapping(value = "/posts/{board_id}/{page}/{no}")
    public String callPostByNo(@PathVariable(value = "board_id") int board_id,
                               @PathVariable(value = "page") int page,
                               @PathVariable(value = "no") int no, Model model) throws Exception {

        PostBean post = bs.callPostByNo(board_id, no);

        model.addAttribute("post", post);
        model.addAttribute("board_id", board_id);
        model.addAttribute("page", page);

        String boardName = bs.getBoardNameById(board_id);
        model.addAttribute("boardName", boardName);

        return "board/postview";
    }

    // 글 수정 폼
    @PostMapping(value = "/posteditform/{board_id}/{page}/{no}")
    public String amendPostForm(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no,
            Model model) throws Exception {

        PostBean postBean = bs.callPostByNo(board_id, no);

        postBean.setContent(postBean.getContent().replace("\n", "<br>"));

        String boardName = "bs.getBoardNameById(board_id)";     // 머지 후 추가

        model.addAttribute("boardName", boardName);
        model.addAttribute("page", page);
        model.addAttribute("PostBean", postBean);
        model.addAttribute("no", no);

        return "board/posteditform";

    }

    // 글 수정
    @PostMapping(value = "/postedit/{board_id}/{page}/{no}")
    public String amendPost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no,
            @ModelAttribute PostBean postBean, Model model) throws Exception {

        PostBean pb = bs.callPostByNo(board_id, no);

        String boardName = bs.getBoardNameById(board_id);

        String result ="";
        int test = postBean.getNo();
        log.info("msg={}",test);
        if (pb.getBoard_id() == postBean.getBoard_id()) {
            bs.amendPost(postBean);
            result = "redirect:/posts/" + board_id + "/" + page + "/" + no;      // 글 상세보기 메소드로 리턴
        } else {
            result = "board/postreditresult";
        }

        model.addAttribute("boardName", boardName);
        model.addAttribute("page", page);
        model.addAttribute("no", no);

        return result;
    }

    // 글 삭제
    @GetMapping(value = "/postdelete/{board_id}/{page}/{no}")
    public String deletePost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no, Model model) throws Exception {

        int result = bs.deletePost(no);

        model.addAttribute("board_id", board_id);
        model.addAttribute("page", page);

        return "redirect:/boards/" + board_id + "/" +page;       // 글 목록 메소드로 리턴
    }

}
