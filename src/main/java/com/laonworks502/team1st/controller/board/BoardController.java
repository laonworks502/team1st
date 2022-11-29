package com.laonworks502.team1st.controller.board;

import com.laonworks502.team1st.service.board.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @GetMapping(value = {"/board/{board_id}", "/board/{board_id}/{page}"})
    public String callBoardList(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page",required = false) Integer page){
        if (page != null) page = 1;




        return null;
    }

    // 글 상세보기
    @GetMapping(value = "/board/{board_id}/{page}/{no}")
    public String callPostByNo(@PathVariable(value = "board_id") int board_id,
                               @PathVariable(value = "page") int page,
                               @PathVariable(value = "no") int no){

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
