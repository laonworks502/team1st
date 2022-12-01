package com.laonworks502.team1st.controller.admin;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.service.admin.AdminServiceImpl;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AdminController {

	@Autowired
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

		// 일별 가입자 수
		int todayJoinTotal = adminservice.todayJoinTotal();
		int ago1JoinTotal = adminservice.ago1JoinTotal();
		int ago2JoinTotal = adminservice.ago2JoinTotal();
		int ago3JoinTotal = adminservice.ago3JoinTotal();
		int ago4JoinTotal = adminservice.ago4JoinTotal();
		int ago5JoinTotal = adminservice.ago5JoinTotal();
		int ago6JoinTotal = adminservice.ago6JoinTotal();
		int ago7JoinTotal = adminservice.ago7JoinTotal();

		model.addAttribute("todayJoinTotal", todayJoinTotal);
		model.addAttribute("ago1JoinTotal", ago1JoinTotal);
		model.addAttribute("ago2JoinTotal", ago2JoinTotal);
		model.addAttribute("ago3JoinTotal", ago3JoinTotal);
		model.addAttribute("ago4JoinTotal", ago4JoinTotal);
		model.addAttribute("ago5JoinTotal", ago5JoinTotal);
		model.addAttribute("ago6JoinTotal", ago6JoinTotal);
		model.addAttribute("ago7JoinTotal", ago7JoinTotal);
		
		
		//주별 가입자 수
		int ago4wJoinTotal = adminservice.ago4wJoinTotal();
		int ago3wJoinTotal = adminservice.ago3wJoinTotal();
		int ago2wJoinTotal = adminservice.ago2wJoinTotal();
		int ago1wJoinTotal = adminservice.ago1wJoinTotal();
		
		model.addAttribute("ago4wJoinTotal", ago4wJoinTotal);
		model.addAttribute("ago3wJoinTotal", ago3wJoinTotal);
		model.addAttribute("ago2wJoinTotal", ago2wJoinTotal);
		model.addAttribute("ago1wJoinTotal", ago1wJoinTotal);
	

		return "admin/adminmain";
	}

	@RequestMapping("adminlogout")
	public String adminlogout(HttpSession session) throws Exception {

		// 관리자 로그인 세션 종료
		session.invalidate();
		log.info("관리자 로그아웃 성공");

		return "redirect:adminloginform";
	}

}

//package com.laonworks502.team1st.controller.admin;
//
//import com.laonworks502.team1st.model.admin.AdminBean;
//import com.laonworks502.team1st.service.admin.AdminService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//
//@Slf4j
//@Controller
//public class AdminController<model> {
//
//    @Autowired
//    private AdminService adminService;
//    
//    //관리자 로그인 폼 진입
//    @RequestMapping(value="adminloginform")
//    public String adiminloginform() throws Exception {
//        log.info("*****관리자 로그인 폼 진입*****");
//
//        return "admin/adminloginform";
//    }
//
//    // 관리자 로그인 하기 
// //   @SuppressWarnings("unlikely-arg-type")
//	@RequestMapping(value = "adminlogin", method = RequestMethod.POST)
//    public String adminlogin(@RequestParam("adminId") String adminId, 
//    						 @RequestParam("adminPw") String adminPw, HttpSession session,
//                                Model model) throws Exception {
//
//        log.info("*****관리자 로그인 실행 메소드 진입*****");
//        log.info("adminId:"+adminId);
//        log.info("adminPw:"+adminPw);
//        
//
//        //관리자 계정 정보 가져오기
//        AdminBean adminBean = adminService.getAdminInfo(adminId);
//        
//        System.out.println("adminBean:"+adminBean);
//        
////        adminService.getAdminInfo(adminPw);
//        
////        AdminBean tempId = new AdminBean();
////        tempId.setId(adminId);
////        tempId.setPasswd(adminPw);
////        
////        adminService.loginAdmin(tempId);
////        
////        <select id="loginAdmin">
////        	select * from admin where id = #{id} and passwd = #{passwd}
////        </select>
//        //폼에서 입력한 계정 정보와 DB에서 가져온 계정 정보 비교
//        int result = 0;
//
//        if (adminPw.equals(adminBean.getPasswd())) {    // 관리자 아이디 && 비밀번호 일치
//
//                log.info("*****관리자 로그인 성공*****");
//                    
//                session.setAttribute("adminId", adminId);
//                
//                log.info("*****관리자 계정정보 세션 공유 성공*****");
//                        
//                model.addAttribute("admeanBean" , adminBean);
//
//                return "admin/adminmain";
//
//            } else {                                                // 관리자 아이디 ||비밀번호 불일치
//
//                log.info("*****관리자 로그인 실패*****");
//                result = 1;
//                model.addAttribute("result", result);
//
//                return "adminloginfail";
//            }
//        } // if - else end
//    }
//
//
//
//
////package com.laonworks502.team1st.controller.admin;
////
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////
////@Controller
////public class AdminController {
////
//////    @Autowired
//////    private AdminServiceImpl adminService;
////
//////    @RequestMapping(value = "adminlogin", method = RequestMethod.GET)
//////            public String adminLogin() {
//////        return "admin/adminlogin";
//////    }
////
//////    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
//////    public String adminmain(Model model, @RequestParam String adminId, @RequestParam String adminPw) {
//////        if(adminId.equals("admin") && adminPw.equals("admin502")){
//////            model.addAttribute("adminId", adminId);
//////            return "adminloginmain";
//////        }
////////        model.put("errorMessage", "아이디나 비밀번호를 확인하세요.");
//////        return "admin/adminlogin";
//////    }
////////
////
////
////    //로그인 폼 뷰
////    @RequestMapping(value = "adminlogin")
////    public String adminlogin() throws Exception {
////        return "admin/adminlogin";
////    }
////
////    // 로그인 인증
////    @RequestMapping(value = "adminloginok", method = RequestMethod.POST)
////    public String member_login_ok(@RequestParam(value = "id", required = false) String id,
////                                  HttpSession session,
////                                  Model model) throws Exception {
////        int result = 0;
////        AdminBean adm = adminService.userCheck(id);
////
////        if (adm == null) {    // 등록되지 않은 회원일때
////
////            result = 1;
////            model.addAttribute("result", result);
////
////            return "admin/adminloginresult";
////
////        } else {            // 등록된 회원일때
////            if (adm.getPasswd().equals(passwd)) {// 비번이 같을때
////                session.setAttribute("id", id);
////
////                String adminid = adm.getId();
////                String adminpasswd = adm.getPasswd();
////
////                model.addAttribute("adminid", adminid);
////                model.addAttribute("adminpw", adminpasswd);
////
////                return "admin/adminmain";
////
////            } else {// 비번이 다를때
////                result = 2;
////                model.addAttribute("result", result);
////
////                return "admin/adminloginresult";
////            }
////        }
////
////    }
////}
////
////
