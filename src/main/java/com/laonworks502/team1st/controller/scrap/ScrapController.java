package com.laonworks502.team1st.controller.scrap;

import com.laonworks502.team1st.model.board.Pagination;
import com.laonworks502.team1st.model.scrap.ScrapBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.scrap.ScrapService;
import com.laonworks502.team1st.service.scrap.ScrapServiceImpl;
import com.laonworks502.team1st.service.users.CommonUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller

@RequestMapping("scrap")
public class ScrapController {

    @Autowired
    private ScrapServiceImpl ss;

    /*[스크랩 검색]*/
    @ResponseBody
    @GetMapping("/{no}")  //경로 설정
    public Integer searchScrap(
            @PathVariable("no") int no,
            HttpSession session) throws Exception {

        log.info("스크랩 검색(searchScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

     //   String email = loginBean.getEmail();
        String email = "a1@naver.com"; //테스트

        ScrapBean scrap = new ScrapBean();

        scrap.setUser_email(email);
        scrap.setNo(no);

        int result = ss.searchScrap(scrap);  //[searchScrap() : 스크랩 정보 검색 메소드]

        return result;
    }

    /*[스크랩 생성(클릭)] : 클릭을 했는지 안했는지를 이미 판별한 상태에서 클릭*/
    @ResponseBody
    @PostMapping("/{no}")
    public Integer insertScrap(
            @PathVariable("no") int no,
            //@RequestBody ScrapBean sb,
            HttpSession session) throws Exception {

        log.info("스크랩 생성(insertScrap)"+no);

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

 //       String email = loginBean.getEmail();

        String email = "a1@naver.com"; //테스트

        Map scrap = new HashMap();
        scrap.put("user_email", email);
        scrap.put("no", no);

        int result = 0;
        result = ss.insertScrap(scrap); //[insertScrap() : 스크랩 생성 메소드]

/*
        int updateResult = 0;
        if(result == 0){
            updateResult = ss.updateScrap(scrap); //[updateScrap]
        }
*/

        return result;

    }


    /*[스크랩 삭제]*/
    @ResponseBody
    @DeleteMapping("/{no}")
    public int deleteScrap(
            @PathVariable("no") int no,
            HttpSession session) throws Exception {  //뷰에따라 model 추가

        log.info("스크랩 삭제(deleteScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();

        ScrapBean scrap = new ScrapBean();

        scrap.setUser_email(email);
        scrap.setNo(no);

        int result = ss.deleteScrap(scrap); //[deleteScrap() : 스크랩 삭제 메소드]

        if(result == 1 ){
            result = 0;
        }else{
            result = 1;
        }

        return result;
    }

    /*[스크랩 전체 출력 리스트]*/
    @ResponseBody
    @GetMapping("/listTotal/{board_id}")
    public String listScrap(
            @PathVariable("board_id") int board_id,
            @RequestParam("page") int page,
             HttpSession session,
             HttpServletRequest request,
              Model model) throws Exception{

        log.info("스크랩 전체 출력 리스트(listTotalScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();

        int listcount = ss.getCount(email); // [getCount() : 총 리스트 수 구해오는 메소드]

        //스크랩 페이지네이션
        Pagination pg = new Pagination(board_id, page, listcount, 50);

        model.addAttribute("pg",pg);

        //스크랩 리스트 전체 출력
        List<ScrapBean> myscrap = ss.listScrap(email, board_id ,pg.getStartPostNo(),pg.getPAGES_COUNT()); //[listScrap() : 스크랩 리스트 출력 메소드]

        model.addAttribute("myscrap", myscrap);


        return "redirect: boardcontent.do?no=";  //추후 설정
    }

    /*[마이페이지) 미니 스크랩 리스트 ]*/
    @ResponseBody
    @GetMapping("/MiniScrap")
    public String listMiniScrap(
                        HttpSession session,
                        HttpServletRequest request,
                        Model model) throws Exception {

        log.info("마이페이지) 미니 스크랩 리스트 (listMiniScrap)");

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

        String email = loginBean.getEmail();

        int listcount = ss.getCount(email); // [getCount() : 총 리스트 수 구해오는 메소드]

         List<ScrapBean> myminiscrap100 =ss.listScrap(email,100,listcount,3);
         List<ScrapBean> myminiscrap200 =ss.listScrap(email,200,listcount,3);
         List<ScrapBean> myminiscrap300 =ss.listScrap(email,300,listcount,3);

        model.addAttribute("myminiscrap100",myminiscrap100);
        model.addAttribute("myminiscrap200",myminiscrap200);
        model.addAttribute("myminiscrap300",myminiscrap300);

        return "redirect: boardcontent.do?no=";  //추후 설정
    }

    /*[테스트]*/
    @GetMapping("/imsi")
    public String imsi(){
        log.info("테스트(테스트)");

        return "imsi/imsi";
    }
}