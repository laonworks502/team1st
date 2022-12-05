package com.laonworks502.team1st.controller.admin;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.service.admin.AdminServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 일별 가입자 수 Refactoring 
	@RequestMapping("adminstat1")
	public String adminstat1(Model model) throws Exception {
		
		log.info("adminstat1 진입");
		
		List<Integer> list = new ArrayList<>();
		
		int n;
		int f;
		
		for (n = 0; n < list.size(); n++) {
			
			f = adminservice.joinTotalDays(n);
				list.add(f);
		}
			
			log.info(list.toString());
		
		model.addAttribute("joinTotalDays", list);
		
		return "admin/adminstat1";
	}
	
	// 일별 가입자 수 차트
//	@RequestMapping("adminstat1")
//	public String adminstat1(Model model) throws Exception {
//		
//		// 일별 가입자 수
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
//		
//		return "admin/adminstat1";
//	}
	
	// 주별 가입자 수 차트
	@RequestMapping("adminstat2")
	public String adminstat2(Model model) throws Exception {
		
		//주별 가입자 수
		int ago4wJoinTotal = adminservice.ago4wJoinTotal();
		int ago3wJoinTotal = adminservice.ago3wJoinTotal();
		int ago2wJoinTotal = adminservice.ago2wJoinTotal();
		int ago1wJoinTotal = adminservice.ago1wJoinTotal();
		
		model.addAttribute("ago4wJoinTotal", ago4wJoinTotal);
		model.addAttribute("ago3wJoinTotal", ago3wJoinTotal);
		model.addAttribute("ago2wJoinTotal", ago2wJoinTotal);
		model.addAttribute("ago1wJoinTotal", ago1wJoinTotal);

		return "admin/adminstat2";
	}

	// 월별 가입자 수 차트
	@RequestMapping("adminstat3")
	public String adminstat3(Model model) throws Exception {
		
		//월별 가입자 수
		int ago12mJoinTotal = adminservice.ago12mJoinTotal();
		int ago11mJoinTotal = adminservice.ago11mJoinTotal();
		int ago10mJoinTotal = adminservice.ago10mJoinTotal();
		int ago9mJoinTotal = adminservice.ago9mJoinTotal();
		int ago8mJoinTotal = adminservice.ago8mJoinTotal();
		int ago7mJoinTotal = adminservice.ago7mJoinTotal();
		int ago6mJoinTotal = adminservice.ago6mJoinTotal();
		int ago5mJoinTotal = adminservice.ago5mJoinTotal();
		int ago4mJoinTotal = adminservice.ago4mJoinTotal();
		int ago3mJoinTotal = adminservice.ago3mJoinTotal();
		int ago2mJoinTotal = adminservice.ago2mJoinTotal();
		int ago1mJoinTotal = adminservice.ago1mJoinTotal();
		
		
		model.addAttribute("ago12mJoinTotal", ago12mJoinTotal);
		model.addAttribute("ago11mJoinTotal", ago11mJoinTotal);
		model.addAttribute("ago10mJoinTotal", ago10mJoinTotal);
		model.addAttribute("ago9mJoinTotal", ago9mJoinTotal);
		model.addAttribute("ago8mJoinTotal", ago8mJoinTotal);
		model.addAttribute("ago7mJoinTotal", ago7mJoinTotal);
		model.addAttribute("ago6mJoinTotal", ago6mJoinTotal);
		model.addAttribute("ago5mJoinTotal", ago5mJoinTotal);
		model.addAttribute("ago4mJoinTotal", ago4mJoinTotal);
		model.addAttribute("ago3mJoinTotal", ago3mJoinTotal);
		model.addAttribute("ago2mJoinTotal", ago2mJoinTotal);
		model.addAttribute("ago1mJoinTotal", ago1mJoinTotal);

		return "admin/adminstat3";
	}
	
	// 전체 회원 목록
	@RequestMapping("userslist")
	public String userslist(AdminBean adminbean, Model model) throws Exception {
    	
    	  // 전체 회원 수 구하기 
    	  int totalUsers = adminservice.countAllUsers();
    	  
    	  model.addAttribute("totalUsers", totalUsers); 
    	  
    	  //현재 활동중인 회원 목록 가져오기
//  		  List<adminbean> userList = adminservice.userList(); 

//    	  Pagination pg = new Pagination(board_id, postTotal, 10);
//          model.addAttribute("pg", pg);
//
//        List<PostBean> postList = bs.callBoardList(board_id, pg.getStartPostNo(), pg.getPAGES_COUNT());
//        model.addAttribute("postList", postList);
//        model.addAttribute("board_id",board_id);
//        String boardName = bs.getBoardNameById(board_id);

        return "admin/userslist";
    }
	
	// 탈퇴 회원 목록
		@RequestMapping("deleteduserslist")
		public String deleteduserslist(Model model) throws Exception {
			return "admin/deleteduserslist";
		}
	
		
}
