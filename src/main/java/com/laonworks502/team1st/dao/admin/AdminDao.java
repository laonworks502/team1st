package com.laonworks502.team1st.dao.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
	
	//관리자 로그인
    public AdminBean getAdminInfo(String id) throws Exception;
    
    // 일별 가입자 수 
    public int usersJoinTotal(int n, String duration) throws Exception;
//    // 최근 7일간 가입자 수 
//    public int todayJoinTotal() throws Exception;
//    public int ago1JoinTotal() throws Exception;
//    public int ago2JoinTotal() throws Exception;
//    public int ago3JoinTotal() throws Exception;
//    public int ago4JoinTotal() throws Exception;
//    public int ago5JoinTotal() throws Exception;
//    public int ago6JoinTotal() throws Exception;
//    public int ago7JoinTotal() throws Exception;
    
    // 최근 4주간 가입자 수 
    public int ago4wJoinTotal() throws Exception;
    public int ago3wJoinTotal() throws Exception;
    public int ago2wJoinTotal() throws Exception;
    public int ago1wJoinTotal() throws Exception;
    
    // 최근 12개월간 가입자 수
    public int ago12mJoinTotal() throws Exception;
    public int ago11mJoinTotal() throws Exception;
    public int ago10mJoinTotal() throws Exception;
    public int ago9mJoinTotal() throws Exception;
    public int ago8mJoinTotal() throws Exception;
    public int ago7mJoinTotal() throws Exception;
    public int ago6mJoinTotal() throws Exception;
    public int ago5mJoinTotal() throws Exception;
    public int ago4mJoinTotal() throws Exception;
    public int ago3mJoinTotal() throws Exception;
    public int ago2mJoinTotal() throws Exception;
    public int ago1mJoinTotal() throws Exception;
    
    // 전체 회원 목록
    public int countAllUsers() throws Exception;
}
