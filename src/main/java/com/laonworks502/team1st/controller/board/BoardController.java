package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.dao.studygroup.board.BoardBean;
import com.laonworks502.team1st.dao.studygroup.board.Pagination;
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
            @RequestParam(value = "page",required = false, defaultValue = "1") Integer page,
            HttpSession Session) throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/boardSearchList");

        int postTotal = boardService.countAllPosts(board_id);

        Pagination pg = new Pagination(board_id, page, postTotal, 10);
        modelAndView.addObject("pg", pg);

        // 리스트 담기
        List<PostBean> postList = boardService.getBoardList(board_id, pg.getStartPostNo(), pg.getPAGES_COUNT());

        modelAndView.addObject("posts", postList);

        // board 정보 담기
        BoardBean boardBean = boardService.getBoardById(board_id);
        modelAndView.addObject("board", boardBean);

        // board 세션 추가
        Session.setAttribute("board_id", board_id);

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
        modelAndView.addObject("page",page);

        return modelAndView;
    }

    // 글 수정 폼
    @GetMapping(value = "/{board_id}/{no}/edit")
    public String updatePostForm(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "no") int no,
            @RequestParam(value = "page") int page,
            Model model) throws Exception {

        PostBean postBean = boardService.getPostByNo(board_id, no);

        postBean.setContent(postBean.getContent().replace("\n", "<br>"));

        model.addAttribute("page", page);
        model.addAttribute("no", no);
        model.addAttribute("PostBean", postBean);

        return "board/posteditform";

    }

    // 글 수정
    @ResponseBody
    @PutMapping (value = "/{board_id}/{no}")
    public Integer updatePost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "no") int no,
            @RequestParam(value = "page") int page,
            @RequestBody PostBean postBean,
            HttpSession session) throws Exception {

        log.info("boards/Put in");

        postBean.setNo(no);

        /*log.info("board_id info log={}", board_id);
        log.info("no info log={}", no);
        log.info("page info log={}", page);
        log.info("info log={}", postBean.getTitle());
        log.info("info log={}", postBean.getContent());*/

        PostBean pb = boardService.getPostByNo(board_id, no);

        int result = 0;
        String email = (String)session.getAttribute("email");

//        if (pb.getWriter().equals(email)) {        // 세션 연결 시 주석 풀기
        result = boardService.amendPost(postBean);
//        }

        log.info("update result: "+result);

        return result;
    }

    // 글 삭제
    @ResponseBody
    @DeleteMapping(value = "/{board_id}/{no}")
    public Integer deletePost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "no") int no,
            HttpSession session) throws Exception {

        log.info("boards/Delete in");

        int result = 0;
        String email = (String)session.getAttribute("email");

//        if (pb.getWriter().equals(email)) {        // 세션 연결 시 주석 풀기
        result = boardService.deletePost(no);
//        }

        return result;       // 글 목록 메소드로 리턴
    }

}
