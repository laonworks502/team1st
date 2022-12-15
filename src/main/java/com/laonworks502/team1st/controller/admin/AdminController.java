package com.laonworks502.team1st.controller.admin;

import com.laonworks502.team1st.model.company.CompanyBean;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.service.admin.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.model.admin.AdminPagination;
import com.laonworks502.team1st.model.users.GeneralUserBean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller 
public class AdminController {
 
	@Autowired
	//@Qualifier("admin")
	private AdminServiceImpl adminservice;

	// 관리자 로그인 폼으로 이동  
	@RequestMapping("adminloginform")
	public String adminloginform() throws Exception {

		return "admin/adminloginform";
	}
	
	// 관리자 로그인
	@RequestMapping("adminlogin")
	public String adminlogin(AdminBean adminbean, HttpSession session, Model model, @RequestParam("id") String id,
			@RequestParam("passwd") String passwd) throws Exception {

		int result = 0;

		adminbean = adminservice.getAdminInfo(id);

		if (adminbean == null) { // 등록되지 않은 회원

			result = 1;
			model.addAttribute("result", result);

			return "admin/adminloginfail";

		} else { // 등록 회원 확인됨
			if (adminbean.getPasswd().equals(passwd)) { // 비번 같아서 로그인됨
				session.setAttribute("id", id);
				log.info("로그인성공");

				return "redirect:adminmain";
			} else { // 비번 달라서 로그인 안됨
				result = 2;
				model.addAttribute("result", result);

				return "admin/adminloginfail";
			}
		}
	}

	// 관리자 메인
	@RequestMapping("adminmain")
	public String adminmain(HttpSession session, Model model) throws Exception {

		// 로그인 세션 유지
		String id = (String) session.getAttribute("id");
		log.info("로그인 세션 유지 ");

		return "admin/adminmain";
	}

	//관리자 로그아웃
	@RequestMapping("adminlogout")
	public String adminlogout(HttpSession session) throws Exception {

		// 관리자 로그인 세션 종료
		session.invalidate();
		log.info("관리자 로그아웃 성공");

		return "redirect:adminloginform";
	}
	
	// 일반회원 일별 가입자 수 
	@RequestMapping("adminstat1")
		public String joinTotalDays(Integer n, Model model) throws Exception {
		
		log.info("adminstat1 진입");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 7; i > 0; i--) {
			 list.add(adminservice.joinTotalDays(i));
		}
			
			log.info(list.toString());
			model.addAttribute("list", list);
		
		
		return "admin/adminstat1";
	}
	
	
	// 일반회원 주별 가입자 수
	@RequestMapping("adminstat2")
		public String joinTotalWeeks(Integer n, Model model) throws Exception {
		
		log.info("adminstat2 진입");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 4; i > 0; i--) {
			 list.add(adminservice.joinTotalWeeks(i));
		}
			
			log.info(list.toString());
			model.addAttribute("list", list);
		
		
		return "admin/adminstat2";
	}
	
	// 일반회원 월별 가입자 수
	@RequestMapping("adminstat3")
		public String joinTotamMonths(Integer n, Model model) throws Exception {
		
		log.info("adminstat3 진입");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 12; i > 0; i--) {
			 list.add(adminservice.joinTotalMonths(i));
		}
			
			log.info(list.toString());
			model.addAttribute("list", list);
		
		
		return "admin/adminstat3";
	}
	
	// 기업회원 일별 가입자 수 
	@RequestMapping("adminstat4")
		public String companyJoinChartDate(Integer n, Model model) throws Exception {
		
		log.info("adminstat4 진입");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 7; i > 0; i--) {
			 list.add(adminservice.companyJoinChartDate(i));
		}
			
			log.info(list.toString());
			model.addAttribute("list", list);
		
		
		return "admin/adminstat4";
	}
	
	// 기업회원 주별 가입자 수
	@RequestMapping("adminstat5")
		public String companyJoinChartWeek(Integer n, Model model) throws Exception {
		
		log.info("adminstat5 진입");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 4; i > 0; i--) {
			 list.add(adminservice.companyJoinChartWeek(i));
		}
			
			log.info(list.toString());
			model.addAttribute("list", list);
		
		
		return "admin/adminstat5";
	}
	
	// 기업회원 월별 가입자 수
	@RequestMapping("adminstat6")
		public String companyJoinChartMonth(Integer n, Model model) throws Exception {
		
		log.info("adminstat6 진입");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 12; i > 0; i--) {
			 list.add(adminservice.companyJoinChartMonth(i));
		}
			
			log.info(list.toString());
			model.addAttribute("list", list);
		
		
		return "admin/adminstat6";
	}
	
	// 전체 일반회원 목록 페이지
	@RequestMapping("generaluserslistpage")
	public String generaluserslistpage(HttpSession session, Model model) throws Exception {

	  // 전체 회원 수 구하기 
  	  int totalUsers = adminservice.countAllUsers();
  	  
  	  log.info("totalUsers: " + totalUsers);
  	  
  	  model.addAttribute("totalUsers", totalUsers); 
  	  
		return "admin/generaluserslist";
	}

	// 전체 일반 회원 목록(테이블)
	@GetMapping("generaluserslist")
	@ResponseBody
	public List<GeneralUserBean> generaluserslist(
			@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, Model model) throws Exception {

		log.info("generaluserslist 진입");

		AdminPagination adminpg = new AdminPagination(page, 20);
		log.info("adminpg: "+adminpg);
		model.addAttribute("adminpg", adminpg);

		List<GeneralUserBean> generalUsersList = adminservice.generalUsersList(page);
		log.info("generalUsersList: "+generalUsersList);
		model.addAttribute("generalUsersList", generalUsersList);

		return generalUsersList;
	}

	//일반 회원 삭제
	@DeleteMapping ("/admingeneraluserdelete")
	@ResponseBody
	public Integer generaluserdelete (
			@RequestBody GeneralUserBean generalUserBean) throws Exception {

		log.info("일반 회원 삭제 컨트롤러 진입 ");

		//특정 회원 삭제
		int result = adminservice.generalUserDelete(generalUserBean.getEmail());

		log.info(generalUserBean.getEmail());
		log.info("일반 회원 삭제 성공");

		return result;
	}


	// 전체 기업 회원 목록 페이지
	@RequestMapping("companyuserslistpage")
	public String companyuserslistpage(HttpSession session, Model model) throws Exception {

		// 전체 회원 수 구하기
		int totalCompanyUsers = adminservice.countAllCompanyUsers();

		log.info("totalCompanyUsers: " + totalCompanyUsers);

		model.addAttribute("totalCompanyUsers", totalCompanyUsers);

		return "admin/companyuserslist";
	}

	// 전체 기업 회원 목록(테이블)
	@GetMapping("companyuserslist")
	@ResponseBody
	public List<CompanyUserBean> companyuserslist(
			@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, Model model) throws Exception {

		log.info("companyuserslist 진입");

		AdminPagination adminpg = new AdminPagination(page, 20);
		log.info("adminpg: "+adminpg);
		model.addAttribute("adminpg", adminpg);

		List<CompanyUserBean> companyUsersList = adminservice.companyUsersList(page);
		log.info("companyUsersList: "+companyUsersList);
		model.addAttribute("companyUsersList", companyUsersList);

		return companyUsersList;
	}

	//기업 회원 삭제
	@DeleteMapping ("/admincompanyuserdelete")
	@ResponseBody
	public Integer admincompanyuserdelete (
			@RequestBody CompanyUserBean companyUserBean) throws Exception {

		log.info("기업 회원 삭제 컨트롤러 진입 ");

		//특정 회원 삭제
		int result = adminservice.companyUserDelete(companyUserBean.getEmail());

		log.info(companyUserBean.getEmail());
		log.info("기업 회원 삭제 성공");

		return result;
	}

	// 전체 기업 목록 페이지
	@RequestMapping("companylistpage")
	public String companylistpage(HttpSession session, Model model) throws Exception {

		// 전체 기업 수 구하기
		int totalCompanies = adminservice.countAllCompanies();

		log.info("totalCompanies: " + totalCompanies);

		model.addAttribute("totalCompanies", totalCompanies);

		return "admin/companylist";
	}

	// 전체 기업 목록(테이블)
	@GetMapping("companylist")
	@ResponseBody
	public List<CompanyBean> companylist(
			@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, Model model) throws Exception {

		log.info("companylist 진입");

		AdminPagination adminpg = new AdminPagination(page, 20);
		log.info("adminpg: "+adminpg);
		model.addAttribute("adminpg", adminpg);

		List<CompanyBean> companyList = adminservice.companyList(page);
		log.info("companyList: "+companyList);
		model.addAttribute("companyList", companyList);

		return companyList;
	}

	// 정규직 게시판 페이지
	@RequestMapping("fulltimeboardpage")
	public String fulltimeboardpage(HttpSession session, Model model) throws Exception {

		// 전체 포스팅 수
		int totalFulltimePosts = adminservice.countAllFulltimePosts();

		log.info("totalFulltimePosts: " + totalFulltimePosts);

		model.addAttribute("totalFulltimePosts", totalFulltimePosts);

		return "admin/fulltimeboard";
	}

	// 정규직 게시판 테이블
	@GetMapping("fulltimeboard")
	@ResponseBody
	public List<PostBean> fulltimeboard(
			@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, Model model) throws Exception {

		log.info("fulltimeboard 진입");

		AdminPagination adminpg = new AdminPagination(page, 20);
		log.info("adminpg: "+adminpg);
		model.addAttribute("adminpg", adminpg);

		List<PostBean> fulltimePostList = adminservice.fulltimePostList(page);
		log.info("fulltimePostList: "+fulltimePostList);
		model.addAttribute("fulltimePostList", fulltimePostList);

		return fulltimePostList;
	}

	//정규직 게시판 게시글 삭제
	@DeleteMapping ("/fulltimepostdelete")
	@ResponseBody
	public Integer fulltimepostdelete (
			@RequestBody PostBean postBean) throws Exception {

		log.info("정규직 게시판 게시글 삭제 컨트롤러 진입 ");

		//정규직 게시판 게시글 삭제
		int result = adminservice.fulltimePostDelte(postBean.getNo());

		log.info(String.valueOf(postBean.getNo()));
		log.info("정규직 게시판 게시글 삭제 성공");

		return result;
	}
}
