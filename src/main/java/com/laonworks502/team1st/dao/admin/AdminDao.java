package com.laonworks502.team1st.dao.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
	
	//관리자 로그인
    public AdminBean getAdminInfo(String id) throws Exception;
    
    // 최근 7일간 가입자 수 
    public int todayJoinTotal() throws Exception;
    public int ago1JoinTotal() throws Exception;
    public int ago2JoinTotal() throws Exception;
    public int ago3JoinTotal() throws Exception;
    public int ago4JoinTotal() throws Exception;
    public int ago5JoinTotal() throws Exception;
    public int ago6JoinTotal() throws Exception;
    public int ago7JoinTotal() throws Exception;
    
    // 최근 4주간 가입자 수
    public int ago4wJoinTotal() throws Exception;
    public int ago3wJoinTotal() throws Exception;
    public int ago2wJoinTotal() throws Exception;
    public int ago1wJoinTotal() throws Exception;
}
