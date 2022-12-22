package com.laonworks502.team1st.controller.studygroup;

import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.studygroup.StudyGroupBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.board.BoardServiceImpl;
import com.laonworks502.team1st.service.studygroup.StudyGroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


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

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        postBean.setWriter(loginBean.getEmail());

        // select key로 얻은 no 값을 보낸 postBean에 set해줌
        boardService.writePost(postBean);
        int no = postBean.getNo();
        log.info("no: {}", no);

        int result = studyGroupService.getPostCountByNo(board_id, no);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("no", no);
        map.put("result", result);

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

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        studyBean.setHost_email(loginBean.getEmail());
        studyBean.setMembers_email(loginBean.getEmail());

        int result = studyGroupService.createMatching(studyBean);
        if (result == 0) {
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

        log.info("insertMatching in");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        StudyGroupBean studyGroupBean = studyGroupService.getStudyByNo(no, email);
        studyGroupBean.setMembers_email(loginBean.getEmail());

        int result = studyGroupService.createMatching(studyGroupBean);

        return result;
    }

    // 스터디 매칭 참여 가능 여부
    @ResponseBody
    @GetMapping(value = "/check/{no}")
    public Integer selectMatching(@PathVariable(value = "no") int no,
                                  @RequestParam String email,
                                  HttpSession session) throws Exception {

        log.info("selectMatching in, email = {}", email);

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        String memberEmail = loginBean.getEmail();

        // 0 또는 1 리턴
        int result = studyGroupService.searchMatching(no, memberEmail);

        StudyGroupBean studyGroupBean = studyGroupService.getStudyByNo(no, email);
        Date date = new Date();

        log.info("데드라인: {}", studyGroupBean.getDeadline());
        log.info("오늘 날짜: {}", date);
        log.info("결과: {}", date.after(studyGroupBean.getDeadline()));

        if (date.after(studyGroupBean.getDeadline())) { // date(현재 날짜)가 deadline보다 이후라면 == 날짜가 지난 경우 true 반환
            result = 2; // 날짜 지남
        }

        int count = studyGroupService.countAllMatching(no, email);

        if ((studyGroupBean.getTotal_members() - count) <= 0) {   // 매칭 인원이 0이거나 그보다 적을 경우
            result = 3; // 매칭 완료
        }

        log.info("result = {}", result);

        return result;
    }
}
    
    
    
    
    // 마이페이지
    
    // 스터디 참여 목록
/*
    @GetMapping(value = "/list")
    public String getSutdyList(HttpSession session) throws Exception{

        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        List<StudyGroupBean> studyGroupBeans = studyGroupService.getStudyList(email);

        return studyGroupBeans;

    }

}
*/
