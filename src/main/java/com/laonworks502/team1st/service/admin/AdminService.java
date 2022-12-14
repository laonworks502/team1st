package com.laonworks502.team1st.service.admin;

import java.util.List;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.model.company.CompanyBean;
import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;

public interface AdminService {

	// 관리자 로그인
    public AdminBean getAdminInfo(String adminId) throws Exception;
    
    //일반회원 일별 가입자 수 
    public int joinTotalDays(int i) throws Exception;
    
    //일반회원 주별 가입자 수
    public int joinTotalWeeks(int i) throws Exception;
    
    //일반회원 월별 가입자 수
    public int joinTotalMonths(int i) throws Exception;
    
    //기업회원 일별 가입자 수
    public int companyJoinChartDate(int i) throws Exception;
    
    //기업회원 주별 가입자 수
    public int companyJoinChartWeek(int i) throws Exception;
    
    //기업회원 월별 가입자 수
    public int companyJoinChartMonth(int i) throws Exception;
    
    //generalusers - 전체 회원 수 구하기
    public int countAllUsers() throws Exception;
    
    //generlauserslist
    public List<GeneralUserBean> generalUsersList(Integer page) throws Exception;

    //companyusers - 전체 기업 회원 수 구하기
    public int countAllCompanyUsers() throws Exception;

    //companyluserlist
    List<CompanyUserBean> companyUsersList(Integer page) throws Exception;

    //companies - 전체 기업 수 구하기
    public int countAllCompanies() throws Exception;

    //companylist
    List<CompanyBean> companyList(Integer page) throws Exception;

    //일반 회원 삭제
    public void generalUserDelete(GeneralUserBean email) throws Exception;

    //countAllFulltimePosts - 전체 정규직 게시글 수 구하기
    public int countAllFulltimePosts() throws Exception;

}
