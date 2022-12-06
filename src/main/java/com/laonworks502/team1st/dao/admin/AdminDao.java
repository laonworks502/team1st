package com.laonworks502.team1st.dao.admin;

import com.laonworks502.team1st.model.admin.AdminBean;

import java.util.List;
import java.util.Map;

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
    
    // 전체 회원 목록
    public int countAllUsers() throws Exception;
}
