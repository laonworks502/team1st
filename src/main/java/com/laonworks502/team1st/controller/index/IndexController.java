package com.laonworks502.team1st.controller.index;

import com.laonworks502.team1st.dao.studygroup.board.BoardBean;
import com.laonworks502.team1st.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    @Qualifier("IndexService")
    private IndexService indexService;

    @RequestMapping(value = {"/test","/"})
    public String index(Model model, HttpSession session) throws Exception {

        List<BoardBean> boardList = indexService.callAllBoards();
        session.setAttribute("boardList", boardList);
        //model.addAttribute("boardList", boardList);


        return "index";
    }

}
