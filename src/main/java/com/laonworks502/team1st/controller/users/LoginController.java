package com.laonworks502.team1st.controller.users;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laonworks502.team1st.model.scrap.ScrapListBean;
import com.laonworks502.team1st.SHA256Util;
import com.laonworks502.team1st.model.users.UserBean;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.users.CompanyUserServiceImpl;
import com.laonworks502.team1st.service.users.GeneralUserServiceImpl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	@Qualifier("company")
	private CompanyUserServiceImpl cus;

	@Autowired
	private GeneralUserServiceImpl gus;

	// 기업 로그인 폼 이동
	@RequestMapping("company-login-form")
	public String loginForm() {
		return "companyuser/loginForm";
	}

	// 일반 로그인 폼
	@RequestMapping("general-login-form")
	public String generaluserloginForm() throws Exception {

		return "generaluser/loginForm";
	}

	// 일반 로그인 실행
	@RequestMapping(value = "/main-mypage", method = RequestMethod.POST)
	public String generaluserlogin_ok(
									  HttpSession session,
									  Model model,
									  GeneralUserBean gub,
									  @RequestParam("email") String email,
									  @RequestParam("passwd") String passwd) throws Exception{

		int result = 0;

		gub = gus.checkGeneraluser(email);

		if(gub == null){    // 등록되지 않은 회원

			result = 1;
			model.addAttribute("result", result);

			return "generaluser/loginResult";

		}else{              // 등록 회원 확인됨
			if(gub.getPasswd().equals(SHA256Util.getEncrypt_gu(passwd, gub.getSalt()))) {        // 암호화한 것과 비번 같아서 로그인됨
//            session.setAttribute("email", email);

				// 뷰에서 입력한 email과 "일반"을 새로운 LoginBean 객체인 loginBean으로 설정
				LoginBean loginBean = new LoginBean(email, "일반");
				// 앞에 새로 생성한 loginBean을 session으로 설정
				session.setAttribute("loginBean", loginBean);

				log.info(loginBean.getAuthority() + "유저 로그인");
				log.info("Email정보 : " + gub.getEmail());
				model.addAttribute("gub", gub);

				return "generaluser/mainMypage";

			} else { // 비번 달라서 로그인 안됨
				result = 2;
				model.addAttribute("result", result);
				log.info("비밀번호 오류");

				return "generaluser/loginResult";
			}
		}
	}

	// 기업 로그인
	@RequestMapping("companylogin_ok")
	public String login(String email, String passwd, Model model, HttpSession session) throws Exception{

		int result = 0;

		// 이메일로 회원 검색
		CompanyUserBean cub = cus.getMember(email);

		if (cub == null) { // 회원이 아닌 경우
			result = 1;
			model.addAttribute("result", result);
		} else { // 회원
			// salt 가져오기
			String salt = cub.getSalt();
			log.info("salt:" + salt);

			// 입력받은 (passwd+salt) 암호화
			String pw = SHA256.getEncrypt(passwd, salt);

			log.info("pw:" + pw);
			if (cub.getPasswd().equals(pw)) {
//				session.setAttribute("email", email);
				LoginBean loginBean = new LoginBean(email, "기업");
				session.setAttribute("loginBean", loginBean);
				log.info("login in");
				log.info("loginUser:" + loginBean.getAuthority());
				return "index"; // 메인 페이지로 이동
			} else {
				result = 2;
				model.addAttribute("result", result);
			}
		}
		return "companyuser/loginResult";
	}

	// 로그아웃
	@RequestMapping("loginselect")
	public String member_logout(HttpSession session) {

		session.removeAttribute("loginBean");

		log.info("logout");
		return "generaluser/loginselect";
	}

	/* [비밀번호 찾기 폼] */
	@RequestMapping("pwfind")
	public String findPasswdUser() {
		log.info("컨트롤러 들어옴(findPasswdUser)");

		return "companyuser/pwfind";
	}

	/* [비번 찾기 메일 보내기] */
	@RequestMapping("pwfind_ok")
	public String member_pw_find_ok(@ModelAttribute CompanyUserBean cub, UserBean ub, HttpServletResponse response,
			Model model) throws Exception {
		log.info("컨트롤러 들어옴(pwfind_ok)");

		response.setContentType("text/html; charset=UTF-8");

		UserBean user = cus.findPasswdUser(ub); // [findPasswdUser()메소드 : 비번 찾기 메소드]

		// 값이 없는 경우
		if (user == null) {

			return "pwresult";

			// 메일 전송
		} else {
			// Mail Server 설정
			String charSet = "utf-8";
			String hostSMTP = "smtp.naver.com";
			String hostSMTPid = ""; // 관리자 이메일
			String hostSMTPpwd = ""; // 비밀번호

			// 보내는 사람
			String fromEmail = ""; // 관리자 이메일
			String fromName = "관리자";
			String subject = "비밀번호 찾기";

			// 받는 사람
			String mail = user.getEmail();

			try {
				HtmlEmail email = new HtmlEmail();
				email.setDebug(true);
				email.setCharset(charSet);
				email.setSSL(true);
				email.setHostName(hostSMTP);
				email.setSmtpPort(587);

				email.setAuthentication(hostSMTPid, hostSMTPpwd);
				email.setTLS(true);
				email.addTo(mail, charSet);
				email.setFrom(fromEmail, fromName, charSet);
				email.setSubject(subject);
				email.setHtmlMsg("<p align = 'center'>비밀번호 찾기</p><br>" + "<div align='center'> 비밀번호 : "
						+ user.getPasswd() + "</div>");
				email.send();
			} catch (Exception e) {
				System.out.println(e);
			}
			model.addAttribute("pwdok", "등록된 email을 확인 하세요.");
			return "companyuser/pwfind";
		}

	}
}
