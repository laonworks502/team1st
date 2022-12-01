package com.laonworks502.team1st.controller.scrap;

import com.laonworks502.team1st.model.scrap.ScrapBean;
import com.laonworks502.team1st.service.scrap.ScrapService;
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

public class ScrapController {

    @Autowired
    private ScrapService ss;

    /*[스크랩 검색]*/
    @GetMapping("/searchScrap/{user_email}/{no}/{date}")  //경로 설정
    public String searchScrap(
            @PathVariable("user_email") String user_email, //세션으로 받을 수도 있음
            @PathVariable("no") int no,
            @PathVariable("date") Timestamp date,
            Model model) throws Exception {

        log.info("스크랩 검색(searchScrap.do)");

        ScrapBean scrap = new ScrapBean();

        scrap.setUser_email(user_email);
        scrap.setNo(no);
        scrap.setDate(date);

        int result = ss.searchScrap(scrap);  //[insertScrap() : 스크랩 정보 검색 메소드]

        model.addAttribute("result", result);  // 0,1 전달

        return "scrap/scrapresult";
    }

    /*[스크랩 생성(클릭)] : 클릭을 했는지 안했는지를 이미 판별한 상태에서 클릭*/
    @PostMapping("/insertScrap/{no}")
    public String insertScrap(
              @PathVariable("no") int no,
              Model model,
              HttpSession session) throws Exception {

        log.info("스크랩 생성(insertScrap.do)");

        String email = (String) session.getAttribute("email");

        Map scrap = new HashMap();
        scrap.put("user_email", email);
        scrap.put("no", no);

        int result = ss.insertScrap(scrap); //[insertScrap() : 스크랩 생성 메소드]

        model.addAttribute("result", result);

        return "scrap/scrapresult";

    }


    /*[스크랩 삭제]*/
    @DeleteMapping("/deleteScrap/{user_email}/{no}/{date}")
    public String deleteScrap(
            @PathVariable("user_email") String user_email,
            @PathVariable("no") int no,
            @PathVariable("date") Timestamp date) throws Exception {  //뷰에따라 model 추가

        log.info("스크랩 삭제(deleteScrap.do)");

        ScrapBean scrap = new ScrapBean();

        scrap.setUser_email(user_email);
        scrap.setNo(no);
        scrap.setDate(date);

        int result = ss.deleteScrap(scrap); //[deleteScrap() : 스크랩 삭제 메소드]

        if(result == 1 ){
            result = 0;
        }else{
            result = 1;
        }

        return "scrap/scrapresult";
    }

    /*[스크랩 전체 출력 리스트]*/
    @GetMapping("/listTotalScrap/{user_email}")
    public String listScrap(
             @PathVariable("user_email") String user_email,
                             HttpSession session,
                             HttpServletRequest request,
                             Model model) throws Exception{
        String email = (String) session.getAttribute("email");

        int listcount = ss.getCount(user_email); // [getCount() : 총 리스트 수 구해오는 메소드]

        //스크랩 리스트 전체 출력
        List<ScrapBean> myscrap100 = ss.listTotalScrap(user_email,100,listcount,50); //[listTotalScrap() : 스크랩 전체 출력 메소드]
        List<ScrapBean> myscrap200 = ss.listTotalScrap(user_email,200,listcount,50);
        List<ScrapBean> myscrap300 = ss.listTotalScrap(user_email,300,listcount,50);

        model.addAttribute("myscrap100", myscrap100);
        model.addAttribute("myscrap200", myscrap200);
        model.addAttribute("myscrap300", myscrap300);

        return "redirect: boardcontent.do?no=";  //추후 설정
    }

    /*[마이페이지) 미니 스크랩 리스트 ]*/
    @GetMapping("/listMiniScrap/{user_email}")
    public String listMiniScrap(
            @PathVariable("user_email") String user_email,
                        HttpSession session,
                        HttpServletRequest request,
                        Model model) throws Exception {
        String email = (String) session.getAttribute("email");

        int listcount = ss.getCount(user_email); // [getCount() : 총 리스트 수 구해오는 메소드]

         List<ScrapBean> myminiscrap100 =ss.listMiniScrap(user_email,100,listcount,3); //[listMiniScrap() : 미니 스크랩 리스트 메소드]
         List<ScrapBean> myminiscrap200 =ss.listMiniScrap(user_email,200,listcount,3);
         List<ScrapBean> myminiscrap300 =ss.listMiniScrap(user_email,300,listcount,3);

        model.addAttribute("myminiscrap100",myminiscrap100);
        model.addAttribute("myminiscrap200",myminiscrap200);
        model.addAttribute("myminiscrap300",myminiscrap300);

        return "redirect: boardcontent.do?no=";  //추후 설정
    }

}