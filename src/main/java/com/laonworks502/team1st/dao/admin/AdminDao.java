package com.laonworks502.team1st.dao.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
	
	//관리자 로그인
    public AdminBean getAdminInfo(String id) throws Exception;
    
    //일별 가입자 수 
    public int joinTotalDays(int i) throws Exception;
    
    //주별 가입자 수
    public int joinTotalWeeks(int i) throws Exception;
    
    //월별 가입자 수
    public int joinTotalMonths(int i) throws Exception;
    
    //generalusers - 전체 회원 수 구하기
    public int countAllUsers() throws Exception;
    
    //generaluserlist
    List<GeneralUserBean> generalUsersList(Integer page) throws Exception;
}
