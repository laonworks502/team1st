package com.laonworks502.team1st.interceptor;

import com.laonworks502.team1st.model.board.BoardBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.index.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
public class NavbarInterceptor implements HandlerInterceptor {
    @Autowired
    @Qualifier("IndexService")
    private IndexService indexService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("NavbarInterceptor");
        HttpSession session = request.getSession();

        // session에서 추출
        List<BoardBean> boardList = (List<BoardBean>) session.getAttribute("boardList");

        if (ObjectUtils.isEmpty(boardList)){
            boardList = indexService.callAllBoards();
            session.setAttribute("boardList", boardList);
            return true;
        } else{
            session.setMaxInactiveInterval(30*60);  // 세션 연장
            return true;
        }

    }
    
}
