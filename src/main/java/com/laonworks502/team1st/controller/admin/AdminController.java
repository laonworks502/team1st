package com.laonworks502.team1st.controller.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.model.admin.AdminPagination;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.service.admin.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller 
public class AdminController {
 
	@Autowired
	//@Qualifier("admin")
	private AdminServiceImpl adminservice;
	
	//가입자 수 통계 관련 변수 정의
	int n;
	String duration;
	int usersJoinTotal;

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
	}
	
	public String adminstat1(Model model) throws Exception {

		// 일별 가입자 수
		for(n = 1; n <= 31; n++ ) {
			model.addAttribute("usersJoinTotal", usersJoinTotal);
		usersJoinTotal = adminservice.usersJoinTotal(n, duration);
		}
		
//				int todayJoinTotal = adminservice.todayJoinTotal();
//				int ago1JoinTotal = adminservice.ago1JoinTotal();
//				int ago2JoinTotal = adminservice.ago2JoinTotal();
//				int ago3JoinTotal = adminservice.ago3JoinTotal();
//				int ago4JoinTotal = adminservice.ago4JoinTotal();
//				int ago5JoinTotal = adminservice.ago5JoinTotal();
//				int ago6JoinTotal = adminservice.ago6JoinTotal();
//				int ago7JoinTotal = adminservice.ago7JoinTotal();
//
//				model.addAttribute("todayJoinTotal", todayJoinTotal);
//				model.addAttribute("ago1JoinTotal", ago1JoinTotal);
//				model.addAttribute("ago2JoinTotal", ago2JoinTotal);
//				model.addAttribute("ago3JoinTotal", ago3JoinTotal);
//				model.addAttribute("ago4JoinTotal", ago4JoinTotal);
//				model.addAttribute("ago5JoinTotal", ago5JoinTotal);
//				model.addAttribute("ago6JoinTotal", ago6JoinTotal);
//				model.addAttribute("ago7JoinTotal", ago7JoinTotal);
		
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
	
	// 전체 회원 목록 페이지
	@RequestMapping("generaluserslistpage")
	public String generaluserslistpage(HttpSession session, Model model) throws Exception {

	  // 전체 회원 수 구하기 
  	  int totalUsers = adminservice.countAllUsers();
  	  
  	  log.info("totalUsers: " + totalUsers);
  	  
  	  model.addAttribute("totalUsers", totalUsers); 
  	  
		return "admin/generaluserslist";
	}


	// 전체 회원 목록(테이블)
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
	
//	// 전체 회원 목록
//	@RequestMapping("userslist")
//	public String userslist(AdminBean adminbean, Model model) throws Exception {
//    	
//    	  // 전체 회원 수 구하기 
//    	  int totalUsers = adminservice.countAllUsers();
//    	  
//    	  model.addAttribute("totalUsers", totalUsers); 
//    	  
//    	  //현재 활동중인 회원 목록 가져오기
//  		  List<adminbean> userList = adminservice.userList(); 
//
////    	  Pagination pg = new Pagination(board_id, postTotal, 10);
////          model.addAttribute("pg", pg);
////
////        List<PostBean> postList = bs.callBoardList(board_id, pg.getStartPostNo(), pg.getPAGES_COUNT());
////        model.addAttribute("postList", postList);
////        model.addAttribute("board_id",board_id);
////        String boardName = bs.getBoardNameById(board_id);
//
//        return "admin/userslist";
//    }
//	
//	// 탈퇴 회원 목록
//		@RequestMapping("deleteduserslist")
//		public String deleteduserslist(Model model) throws Exception {
//			return "admin/deleteduserslist";
//		}
	
}
