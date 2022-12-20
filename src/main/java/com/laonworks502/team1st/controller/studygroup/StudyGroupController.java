package com.laonworks502.team1st.controller.studygroup;

import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.studygroup.StudyGroupBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.board.BoardService;
import com.laonworks502.team1st.service.board.BoardServiceImpl;
import com.laonworks502.team1st.service.studygroup.StudyGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/study")

public class StudyGroupController {
    @Autowired
    private StudyGroupServiceImpl studyGroupService;

    @Autowired
    private BoardServiceImpl boardService;


    // 글 작성 폼 (스터디 게시판)
    @GetMapping(value = "/{board_id}/write")
    public String studypostwriteform(
            @PathVariable("board_id") int board_id,
            @RequestParam("page") int page,
            Model model) throws Exception {

        model.addAttribute("page", page);
        model.addAttribute("board_id", board_id);

        return "board/studypostwrite";
    }

    // 글 작성 (스터디 게시판)
    @ResponseBody
    @PostMapping(value = "/{board_id}")
    public Map<String, Object> studyWritePost(
            @PathVariable(value = "board_id") int board_id,
            @RequestParam(value = "page") int page,
            @RequestBody PostBean postBean, HttpSession session) throws Exception {

        log.info("studyWritePost in");

        postBean.setBoard_id(board_id);

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        postBean.setWriter(loginBean.getEmail());

        // select key로 얻은 no 값을 보낸 postBean에 set해줌
        boardService.writePost(postBean);
        int no = postBean.getNo();
        log.info("no: {}", no);

        int result = studyGroupService.getPostCountByNo(board_id, no);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("no",no);
        map.put("result",result);

        log.info("no: {}", no);
        log.info("result: {}", result);

        return map;
    }

    // 매칭 테이블 생성
    @ResponseBody
    @PostMapping(value = "/macthing/{board_id}/{no}")
    public <studyBean> Integer createmacthing(
            @PathVariable(value = "board_id") int board_id,
            @PathVariable(value = "no") int no,
            @RequestBody StudyGroupBean studyBean,
            HttpSession session) throws Exception {

        studyBean.setNo(no);

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        studyBean.setHost_email(loginBean.getEmail());
        studyBean.setMembers_email(loginBean.getEmail());

        int result = studyGroupService.createMatching(studyBean);
        if(result == 0){
            // no 값으로 작성 글 delete 메소드
            int deleteResult = boardService.deletePost(no);
        }

        return result;
    }

    // 스터디 매칭 참여
    @ResponseBody
    @PostMapping(value = "/matching/{no}")
    public Integer insertMatching(
            @PathVariable(value = "no") int no,
            @RequestBody String email,
            HttpSession session) throws Exception {

        log.info("들어옴 {}", email);

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        StudyGroupBean studyGroupBean = studyGroupService.getStudyByNo(no, email);
        studyGroupBean.setMembers_email(loginBean.getEmail());

        int result = studyGroupService.createMatching(studyGroupBean);

        return result;
    }
    
    // 스터디 매칭 참여 가능 여부
    @ResponseBody
    @GetMapping(value = "/{no}")
    public Integer selectMatching(@PathVariable(value = "no") int no,
                               HttpSession session) throws Exception {

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        int result = studyGroupService.searchMatching(no, email);

        log.info("result = {}", result);

        return result;
    }


}
