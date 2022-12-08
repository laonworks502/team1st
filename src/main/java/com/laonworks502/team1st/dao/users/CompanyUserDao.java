package com.laonworks502.team1st.dao.users;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.laonworks502.team1st.model.company.CompanyBean;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.UserBean;

@Mapper
public interface CompanyUserDao {
	
	// 기업 등록
	public int joinCompany(CompanyBean cb) throws Exception; 
	
	// 기업 회원 가입
	public int CompanyjoinUser(UserBean userBean) throws Exception;
	
	// 기업 회원 정보 수정
	public int amendUser(UserBean userBean) throws Exception;
	
	// 이메일 중복검사
	public int emailCheck(String email) throws Exception;

	// 이메일로 회원 검색
	public CompanyUserBean getMember(String email);
	
	/*[비번 찾기 메일 보내기]*/
	public CompanyUserBean findPasswdCompanyUser(UserBean userBean);

	// 회원 탈퇴
	public int quitUser(UserBean userBean) throws Exception;

	// 기업명으로 기업정보 불러오기
	public CompanyBean getCompany(String company_name) throws Exception;

	// 기업회원이 작성한 모든 총 게시물 수 구하기
	public int countAllCompanyPosts(String writer);

	// 리스트 담기 
	public List<PostBean> getCompanyBoardList(
			@Param("writer") String writer,@Param("startPostNo") int startPostNo,@Param("PAGES_COUNT") int pages_COUNT);

	
}
