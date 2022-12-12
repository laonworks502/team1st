package com.laonworks502.team1st.controller.scrap;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.model.board.Pagination;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.scrap.ScrapBean;
import com.laonworks502.team1st.model.scrap.ScrapListBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.board.BoardServiceImpl;
import com.laonworks502.team1st.service.scrap.ScrapServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller

@RequestMapping("scrap")
public class ScrapController {

    @Autowired
    private ScrapServiceImpl ss;
    @Autowired
    private BoardServiceImpl bs;

    /*[스크랩 검색]*/
    @GetMapping("search/{no}")  //경로 설정
    public String searchScrap(
            @PathVariable("no") int no,
            HttpSession session,
            Model model) throws Exception {

        log.info("스크랩 검색(searchScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();
        //String email = "a1@naver.com"; //테스트

        ScrapBean scrap = new ScrapBean();

        scrap.setUser_email(email);
        scrap.setNo(no);

        int result = ss.searchScrap(scrap);  //[searchScrap() : 스크랩 정보 검색 메소드]

        model.addAttribute("result",result);


        return "boards";
    }

    /*[스크랩 생성(클릭)] : 클릭을 했는지 안했는지를 이미 판별한 상태에서 클릭*/
    @ResponseBody
    @PostMapping("/{no}")
    public Integer insertScrap(
            @PathVariable("no") int no,
            //@RequestBody ScrapBean sb,
            HttpSession session) throws Exception {

        log.info("스크랩 컨트롤러");

        //LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        //String email = loginBean.getEmail();
        String email = "a1@naver.com"; //테스트

        ScrapBean scrap = new ScrapBean();

        scrap.setUser_email(email);
        scrap.setNo(no);

        int result = ss.searchScrap(scrap);  //[searchScrap() : 스크랩 정보 검색 메소드]
        log.info("스크랩 검색( searchScrap)"+result);

        int scrapresult;

        if(result == 1){

            int deleteresult = ss.deleteScrap(scrap); //[deleteScrap() : 스크랩 삭제 메소드]
            log.info("스크랩 삭제(deleteScrap)"+ deleteresult);
            if(deleteresult == 0){
                scrapresult = 1;   //스크랩 O 아이콘 나타남
            }else{
                scrapresult = 0;   //스크랩 X 아이콘 나타남
            }
        }else{
           scrapresult = ss.insertScrap(scrap); //[insertScrap() : 스크랩 생성 메소드]
            log.info("스크랩 생성(insertScrap)"+ scrapresult);
        }

        return scrapresult;

    }


    /*[전체 해당 페이지에 대한 검색]*/
/*
    @ResponseBody
    @GetMapping("/boardSearchList")
    public List<Integer> boardSearchList(
            @RequestBody Pagination pg,
            @RequestBody List<PostBean> posts,
            HttpSession session,
            Model model)throws Exception{

        log.info("전체 해당 페이지에 대한 검색(boardSearchList)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();

        List<Integer> boardSearchList = new ArrayList<>();

        for(int i =0;  i > pg.getPAGES_COUNT(); i++) {
            boardSearchList.add(ss.getBoardSearchList(email, posts.get(i).getNo()));
        }

        model.addAttribute("boardSearchList",boardSearchList);

        return boardSearchList;

    }
*/


    /*[스크랩 전체 출력 리스트]*/
    @GetMapping("/listTotalScrap/{board_id}")
    public String listTotalScrap(
            @PathVariable("board_id") int board_id,
            @RequestParam(value="page", required = false, defaultValue = "1") Integer page,
            HttpSession session,
            Model model) throws Exception{

        log.info("스크랩 전체 출력 리스트(listTotalScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();

        int listcount = ss.getCount(email); // [getCount() : 총 리스트 수 구해오는 메소드]

        //스크랩 페이지네이션
        Pagination pg = new Pagination(board_id, page, listcount, 50);

        model.addAttribute("pg",pg);

        //스크랩 리스트 전체 출력
        List<ScrapListBean> myscrap = ss.listTotalScrap(email, board_id ,pg.getStartPostNo(),pg.getPAGES_COUNT()); //[listScrap() : 스크랩 리스트 출력 메소드]

        log.info("myscrap"+myscrap);

        model.addAttribute("myscrap", myscrap);

        String boardName = bs.getBoardNameById(board_id); //[getBoardNameById() : 이름 구하는 메소드]

        model.addAttribute("boardName",boardName);

        return "generaluser/totalscrap";
    }


    /*[마이페이지) 미니 스크랩 리스트 ]*/
    @GetMapping("/listMiniScrap")
    public String listMiniScrap(
            GeneralUserBean gub,
            HttpSession session,
            Model model) throws Exception {

        log.info("마이페이지) 미니 스크랩 리스트 (listMiniScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();

        //int listcount = ss.getCount(email); // [getCount() : 총 리스트 수 구해오는 메소드]

        List<ScrapListBean> myminiscrap100 =ss.listMiniScrap(email,100);
        List<ScrapListBean> myminiscrap200 =ss.listMiniScrap(email,200);
        List<ScrapListBean> myminiscrap300 =ss.listMiniScrap(email,300);

        model.addAttribute("myminiscrap100",myminiscrap100);
        model.addAttribute("myminiscrap200",myminiscrap200);
        model.addAttribute("myminiscrap300",myminiscrap300);

        model.addAttribute("gub",gub);

        log.info("myminiscrap100" +myminiscrap100);

        return "generaluser/generalmypage";
    }


}