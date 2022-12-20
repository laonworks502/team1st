package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.model.board.Pagination;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.post.PostListBean;
import com.laonworks502.team1st.model.studygroup.StudyGroupBean;
import com.laonworks502.team1st.model.scrap.ScrapBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.board.BoardService;
import com.laonworks502.team1st.service.scrap.ScrapServiceImpl;
import com.laonworks502.team1st.service.studygroup.StudyGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    @Qualifier("BoardService")
    private BoardService boardService;

    @Autowired
    private ScrapServiceImpl ss;

    @Autowired
    private StudyGroupServiceImpl studyGroupService;

    // 글 작성 폼
    @GetMapping(value = "/{board_id}/write")
    public ModelAndView postwriteform(
            @PathVariable("board_id") int board_id,
            @RequestParam("page") int page) throws Exception {

        if (boardService.checkBoardExist(board_id) != 1) {
            ModelAndView modelAndView = new ModelAndView("board/wrong-access");
            return modelAndView;
        }

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

        if (boardService.checkBoardExist(board_id) != 1) {
            ModelAndView modelAndView = new ModelAndView("board/wrong-access");
            return modelAndView;
        }

        post.setBoard_id(board_id);
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        post.setWriter(loginBean.getEmail());

        boardService.writePost(post);
        int no = post.getNo();

        ModelAndView modelAndView = new ModelAndView("redirect:/boards/" + board_id + "/" + no, "page", page);
        BoardBean boardBean = boardService.getBoardById(board_id);
        modelAndView.addObject("board", boardBean);

        return modelAndView;
    }

    // 글 목록
    @GetMapping(value = "/{board_id}")
    public ModelAndView getBoardList(
            @PathVariable(value = "board_id") int board_id,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            HttpSession session) throws Exception {

        if (boardService.checkBoardExist(board_id) != 1) {
            ModelAndView modelAndView = new ModelAndView("board/wrong-access");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("board/boardlist");

        int postTotal = boardService.countAllPosts(board_id);

        Pagination pg = new Pagination(board_id, page, postTotal, 10);
        log.info("페이지 값 : {}", pg.getPage());

        if (page > pg.getPagesTotal()&&pg.getPagesTotal() != 0) {
            modelAndView.setViewName("board/wrong-access");
            return modelAndView;
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("pg", pg);

        // 리스트 담기
        List<PostListBean> postList = boardService.getBoardList(board_id, pg.getStartPostNo(), pg.getPAGES_COUNT());

        log.info("postList={}", postList);

        // 스크랩 담기
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        if (loginBean != null) {

            String email = loginBean.getEmail();
            //String email = "a1@naver.com";

            //postList에 대한 스크랩 유무 검색 메소드
            for (int i = 0; i < postList.size(); i++) {
                postList.get(i).setScrapResult(ss.getBoardSearchList(email, postList.get(i).getNo()));

                log.info("postList={}", postList.get(i).getScrapResult());

            }
        }

        modelAndView.addObject("posts", postList);


        // board 정보 담기
        BoardBean boardBean = boardService.getBoardById(board_id);
        modelAndView.addObject("board", boardBean);


        // board 세션 추가
        session.setAttribute("board_id", board_id);

        return modelAndView;
    }

    // 글 상세보기
    @GetMapping(value = "/{board_id}/{no}")
    public ModelAndView getPostByNo(@PathVariable(value = "board_id") int board_id,
                                    @PathVariable(value = "no") int no,
                                    @RequestParam(value = "page",required = false, defaultValue = "1") Integer page ,
                                    HttpSession session)throws Exception {
                                    
        ModelAndView modelAndView = new ModelAndView("board/postview");

        if (boardService.checkBoardExist(board_id) != 1) {
            modelAndView.setViewName("board/wrong-access");
            return modelAndView;
        }
        
        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        if (loginBean != null) {
            String email = loginBean.getEmail();
            
            ScrapBean scrap = new ScrapBean();

            scrap.setUser_email(email);
            scrap.setNo(no);

            int result = ss.searchScrap(scrap);  //[searchScrap() : 스크랩 정보 검색 메소드]
            modelAndView.addObject("result", result);
        }

        PostBean post = boardService.getPostByNo(board_id, no);
        post.setContent(post.getContent().replace("\n", "<br>"));
        
        modelAndView.addObject("post", post);
        

        BoardBean board = boardService.getBoardById(board_id);
        modelAndView.addObject("board", board);
        modelAndView.addObject("page", page);

        log.info("post.getWriter() = {}", post.getWriter());

        if(board_id == 300){
            StudyGroupBean sgb = studyGroupService.getStudyByNo(no, post.getWriter());
            int studyCount = studyGroupService.countAllMatching(no, post.getWriter());
            modelAndView.addObject("sgb", sgb);
            modelAndView.addObject("studyCount", studyCount);
        }

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
    @PutMapping(value = "/{board_id}/{no}")
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
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        try {
            if (pb.getWriter().equals(loginBean.getEmail())) {        // 세션 연결 시 주석 풀기
                result = boardService.amendPost(postBean);
            }

        }catch (Exception e){
            log.info("error : {}", e);
        }

        log.info("update result ={}", result);

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

        PostBean pb = boardService.getPostByNo(board_id, no);

        int result = 0;
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        if (pb.getWriter().equals(loginBean.getEmail())) {        // 세션 연결 시 주석 풀기
            result = boardService.deletePost(no);
        }

        return result;       // 글 목록 메소드로 리턴
    }

}
