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
    @GetMapping(value = "")
    public String callBoardList(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "page",required = false) int page){

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
