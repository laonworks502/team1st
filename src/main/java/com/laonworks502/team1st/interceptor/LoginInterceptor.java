package com.laonworks502.team1st.interceptor;

import com.laonworks502.team1st.model.users.LoginBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor");
        HttpSession session = request.getSession();

        // session에서 추출
        LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");

        if (ObjectUtils.isEmpty(loginBean)){    // 비로그인시
            log.info("비로그인");
            response.sendRedirect("/loginselect");
            return false;
        } else{                                 // 로그인시
            log.info("로그인");
            session.setMaxInactiveInterval(30*60);  // 세션 연장
            return true;

        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
