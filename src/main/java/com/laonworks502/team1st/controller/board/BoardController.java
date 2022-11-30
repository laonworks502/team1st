package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.service.board.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    private BoardServiceImpl bs;

    // 글 작성
    @PostMapping(value = "")
    public String writePost(HttpSession session){
        return null;
    }

    // 글 목록
    @GetMapping(value = "")
    public String callBoardList(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page",required = false) int page){

        return null;
    }

    // 글 수정 폼
    @PostMapping(value = "/posteditform/{board_id}/{page}/{no}")
    public String amendPostForm(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no,
            Model model) throws Exception {

        PostBean postBean = bs.callPostByNo(no);

        postBean.setContent(postBean.getContent().replace("\n", "<br>"));

        String boardName = "bs.getBoardNameById(board_id)";     // 머지 후 추가

        model.addAttribute("boardName", boardName);
        model.addAttribute("page", page);
        model.addAttribute("PostBean", postBean);

        return "post/posteditform" + "/" + boardName + "/" + page + "/" + no;

    }

    // 글 수정
    @PostMapping(value = "/postedit/{board_id}/{page}/{no}")
    public String amendPost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no,
            @RequestParam PostBean postBean, Model model) throws Exception {

        int no2;

        PostBean pb = bs.callPostByNo(no);

        if (pb.getBoard_id().equals(postBean.getBoard_id())) {
            no2 = bs.amendPost(postBean);
        } else {
            return null;        // 글 수정 삭제 메세지 띄우든지 멀 하든지 ..
        }

        String boardName = "bs.getBoardNameById(board_id)";
        //  boardName = bs.getBoardNameById(board_id);

        model.addAttribute("boardName", boardName);
        model.addAttribute("page", page);
        model.addAttribute("no", no2);

        return "redirect:/" + boardName + "/" + page + "/" + no2;    // 글 상세보기 메소드로 리턴
    }

    // 글 삭제
    @PostMapping(value = "/postdelete/{board_id}/{page}/{no}")
    public String deletePost(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page") int page,
            @PathVariable(value = "no") int no, Model model) throws Exception {

        int result = bs.deletePost(no);

        model.addAttribute("board_id", board_id);
        model.addAttribute("page", page);

        return "redirect:/board/" + board_id + "/" +page;       // 글 목록 메소드로 리턴
    }

}
