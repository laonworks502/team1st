package com.laonworks502.team1st.controller.users;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.laonworks502.team1st.model.board.Pagination;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.laonworks502.team1st.model.company.CompanyBean;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.users.CompanyUserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/company")
public class CompanyUserController {

	@Autowired
	@Qualifier("company")
	private CompanyUserServiceImpl cus;

	// 로그인폼에서 회원가입 클릭시 기업 등록폼으로 이동
	@RequestMapping("/postcompanyuser")
	public String test() {
		return "companyuser/companyinsert";
	}

	// 기업회원 마이페이지
	@RequestMapping("/companymypage")
	public String companymypage() {
		return "companyuser/companymypage";
	}

	// 기업 회원 마이페이지 작성글 목록
	@RequestMapping("/companywritelist")
	public String mypage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			HttpServletRequest request, HttpSession session, Model model, String writer) throws Exception {
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();
		log.info(email + "의 mypage in");

		// 기업회원이 작성한 모든 총 게시물 수 구하기
		writer = email;
		log.info("writer:" + writer);
		int postTotal = cus.countAllCompanyPosts(writer);
		log.info("회원 총 게시물 수:" + postTotal);

		Pagination pg = new Pagination(100, postTotal, 10);
		model.addAttribute("pg", pg);

		// 리스트 담기
		List<PostBean> postList = cus.getCompanyBoardList(writer, pg.getStartPostNo(), pg.getPAGES_COUNT());
		model.addAttribute("posts", postList);
		return "companyuser/companywritelist";
	}

	// 이메일 중복체크 및 형식 검사
	@PostMapping("/emailcheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email, boolean vali) throws Exception {
		
		int cnt = 0;
		vali = false;

		int em = cus.emailCheck(email);
		if(em==0) {
			
			if(StringUtils.isEmpty(email)){
				vali=false;
			}
			String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(email);
			if(m.matches()) {
				vali = true;
			}
			if(vali==false) {
				cnt=1;
			}else {
				cnt=0;
			}
		}else {
			cnt=1;
		}
		return cnt;
	}

	// 기업 등록
	@RequestMapping("/companyinsert_ok")
	public String memberinsert_ok(CompanyBean cb, HttpServletRequest request, HttpSession session, Model model)
			throws Exception {
		cus.joinCompany(cb);
		log.info("companyinsert_ok!");

		model.addAttribute("company_name", cb.getCompany_name());
		model.addAttribute("cb", cb);

		return "companyuser/companyuserinsert"; // 기업 등록 후 기업 회원 등록폼으로 이동
	}

	// 기업 회원 등록
	@RequestMapping("/companyuserinsert_ok")
	public String memberinsert_ok2(CompanyUserBean cub) throws Exception {

		// salt 생성
		String salt = SHA256.getSalt();

		// 생성된 salt저장
		cub.setSalt(salt);
		String passwd = cub.getPasswd();

		// 최종 비밀번호 : 입력받은 passwd + salt 를 암호화
		String lastpwd = SHA256.getEncrypt(passwd, salt);
		cub.setPasswd(lastpwd);

		log.info("salt in" + cub.getSalt());
		log.info("passwd in" + passwd);
		log.info("passwd:" + lastpwd);

		// 회원 가입 실행
		cus.joinUser(cub);
		log.info("companyuserinsert_ok!");
		return "redirect:/company-login-form"; // 가입완료 후 다시 로그인폼으로 이동
	}

	// 기업 회원 수정
	@RequestMapping("/update")
	public String companyuserupdate(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		log.info("수정컨트롤러");
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();

		// 이메일로 회원 정보 찾기
		CompanyUserBean cub = cus.getMember(email);
		log.info("수정폼에 불러오기:email:" + email);

		model.addAttribute("cub", cub);
		return "companyuser/companyuserupdate";
	}

	// 기업 회원 수정
	@RequestMapping("/companyuserupdate_ok")
	public String companyuserupdate_ok(@RequestParam("passconfirm") String passconfirm, HttpSession session,
			CompanyUserBean cub, HttpServletRequest request, Model model) throws Exception {

		// 이메일로 회원 검색
		CompanyUserBean cubean = cus.getMember(cub.getEmail());

		String salt = cubean.getSalt();
		log.info("salt:" + salt);

		// 입력받은 (passconfirm+salt) 암호화
		String pw = SHA256.getEncrypt(passconfirm, salt);

		int result = 0;
		if (cubean.getPasswd().equals(pw)) { // 비밀번호가 일치하면 아래 수정메소드 실행
			cus.amendUser(cub);
			log.info("companyuserupdate_ok");
			return "redirect:/companymypage"; // 회원 정보 수정 완료 후 다시 마이페이지로 이동
		} else { // 불일치시 아래 오류페이지로 이동
			result = 3;
			model.addAttribute("result", result);
		}
		return "companyuser/loginResult";

	}

	// 기업 회원 탈퇴
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		log.info("탈퇴컨트롤러");
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();

		CompanyUserBean cub = cus.getMember(email);

		log.info("탈퇴폼에 불러오기:email:" + email);

		model.addAttribute("cub", cub);

		return "companyuser/companyuserdelete";
	}

	// 기업 회원 탈퇴
	@RequestMapping("/companyuserdelete_ok")
	public String companyuserdelete_ok(@RequestParam("exit_reason") String exit_reason,
			@RequestParam("passconfirm") String passconfirm, HttpSession session, HttpServletRequest request,
			Model model) throws Exception {
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();

		// 이메일로 회원 검색
		CompanyUserBean cub = cus.getMember(email);

		String salt = cub.getSalt();
		log.info("salt:" + salt);

		// 입력받은 (passconfirm+salt) 암호화
		String pw = SHA256.getEncrypt(passconfirm, salt);

		int result = 0;
		if (cub.getPasswd().equals(pw)) { // 비밀번호가 일치하면 아래 탈퇴메소드 실행
			// 탈퇴 사유 입력
			cub.setExit_reason(exit_reason);
			log.info(cub.getExit_reason());

			// 탈퇴 메서드 실행
			cus.quitUser(cub);
			log.info("companyuserdelete_ok");

			// 세션 종료
			session.invalidate();

			result = 4;
			model.addAttribute("result", result); // 회원 탈퇴 완료 후 메인으로 이동
		} else { // 불일치시 아래 오류페이지로 이동
			result = 3;
			model.addAttribute("result", result);
		}
		return "companyuser/loginResult";
	}

}
