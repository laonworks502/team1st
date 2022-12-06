package com.laonworks502.team1st.controller.index;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.service.index.IndexServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IndexServiceImpl indexService;

    @RequestMapping(value = {"/test","/"})
    public String index(Model model, HttpSession session) throws Exception {

        List<BoardBean> boardList = indexService.callAllBoards();
        session.setAttribute("boardList", boardList);
        //model.addAttribute("boardList", boardList);


        return "index";
    }

}
