package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.model.board.Pagination;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    @Qualifier("BoardService")
    private BoardService boardService;

    // 글 작성 폼
    @GetMapping(value = "/{board_id}/write")
    public ModelAndView postwriteform(
            @PathVariable("board_id") int board_id,
            @RequestParam("page") int page) throws Exception {
        ModelAndView modelAndView = new ModelAndView("board/postwrite");

        BoardBean boardBean = boardService.getBoardById(board_id);
        modelAndView.addObject("board", boardBean);
        modelAndView.addObject("page", page);

        return modelAndView;
    }

    // 글 작성
    @PostMapping(value = "/{board_id}")
    public ModelAndView writePost(
            @PathVariable(value = "board_id") int board_id,
            @RequestParam(value = "page") int page,
            @ModelAttribute PostBean post, HttpSession session) throws Exception {

        post.setBoard_id(board_id);
        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        post.setWriter(loginBean.getEmail());

        int no = boardService.writePost(post);

        ModelAndView modelAndView = new ModelAndView("redirect:/"+ board_id + "/" + no, "page", page);
        BoardBean boardBean = boardService.getBoardById(board_id);
        modelAndView.addObject("board", boardBean);

        return  modelAndView;
    }

    // 글 목록
    @GetMapping(value = "/{board_id}")
    public ModelAndView getBoardList(
            @PathVariable(value = "board_id") int board_id,
            @RequestParam(value = "page",required = false, defaultValue = "1") Integer page) throws Exception {

        ModelAndView modelAndView = new ModelAndView("board/boardlist");

        int postTotal = boardService.countAllPosts(board_id);

        Pagination pg = new Pagination(board_id, postTotal, 10);
        modelAndView.addObject("pg", pg);

        // 리스트 담기
        List<PostBean> postList = boardService.getBoardList(board_id, pg.getStartPostNo(), pg.getPAGES_COUNT());
        modelAndView.addObject("posts", postList);

        // board 정보 담기
        BoardBean boardBean = boardService.getBoardById(board_id);
        modelAndView.addObject("board", boardBean);

        return modelAndView;
    }

    // 글 상세보기
    @GetMapping(value = "/{board_id}/{no}")
    public ModelAndView getPostByNo(@PathVariable(value = "board_id") int board_id,
                              @PathVariable(value = "no") int no,
                              @RequestParam(value = "page") int page) throws Exception {

        ModelAndView modelAndView = new ModelAndView("board/postview");

        PostBean post = boardService.getPostByNo(board_id, no);
        post.setContent(post.getContent().replace("\n", "<br>"));

        modelAndView.addObject("post", post);

        BoardBean board = boardService.getBoardById(board_id);
        modelAndView.addObject("board",board);

        return modelAndView;
    }

    // 글 수정 폼
    @PostMapping(value = "/posteditform/{board_id}/{page}/{no}")
    public String amendPostForm(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no,
            Model model) throws Exception {

        PostBean postBean = boardService.getPostByNo(board_id, no);

        postBean.setContent(postBean.getContent().replace("\n", "<br>"));

        String boardName = "boardService.getBoardNameById(board_id)";     // 머지 후 추가

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

        PostBean pb = boardService.getPostByNo(board_id, no);

        String boardName = boardService.getBoardNameById(board_id);

        String result ="";
        int test = postBean.getNo();
        log.info("msg={}",test);
        if (pb.getBoard_id() == postBean.getBoard_id()) {
            boardService.amendPost(postBean);
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

        int result = boardService.deletePost(no);

        model.addAttribute("board_id", board_id);
        model.addAttribute("page", page);

        return "redirect:/boards/" + board_id + "/" +page;       // 글 목록 메소드로 리턴
    }

}
