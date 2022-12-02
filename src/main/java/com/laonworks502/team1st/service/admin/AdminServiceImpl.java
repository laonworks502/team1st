package com.laonworks502.team1st.service.admin;


import com.laonworks502.team1st.dao.admin.AdminDao;
import com.laonworks502.team1st.model.admin.AdminBean;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("admin")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao admindao;

    // 관리자 로그인
    public AdminBean getAdminInfo(String id) throws Exception {
        return admindao.getAdminInfo(id);
    }
    
    public int joinTotalDays(int n) throws Exception{
    	return admindao.joinTotalDays(n);
    }
    
    // 최근 7일간 가입자 수 
//    public int todayJoinTotal() throws Exception{
//    	return admindao.todayJoinTotal();
//    }
//    public int ago1JoinTotal() throws Exception{
//    	return admindao.ago1JoinTotal();
//    }
//    
//    public int ago2JoinTotal() throws Exception{
//    	return admindao.ago2JoinTotal();
//    }
//    
//    public int ago3JoinTotal() throws Exception{
//    	return admindao.ago3JoinTotal();
//    }
//    
//    public int ago4JoinTotal() throws Exception{
//    	return admindao.ago4JoinTotal();
//    }
//    
//    public int ago5JoinTotal() throws Exception{
//    	return admindao.ago5JoinTotal();
//    }
//    
//    public int ago6JoinTotal() throws Exception{
//    	return admindao.ago6JoinTotal();
//    }
//    
//    public int ago7JoinTotal() throws Exception{
//    	return admindao.ago7JoinTotal();
//    }

    // 주별 가입자 수 
    public int ago4wJoinTotal() throws Exception{
    	return admindao.ago4wJoinTotal();
    }
    
    public int ago3wJoinTotal() throws Exception{
    	return admindao.ago3wJoinTotal();
    }
    
    public int ago2wJoinTotal() throws Exception{
    	return admindao.ago2wJoinTotal();
    }
    
    public int ago1wJoinTotal() throws Exception{
    	return admindao.ago1wJoinTotal();
    }
    
    // 월별 가입자 수
    public int ago12mJoinTotal() throws Exception{
    	return admindao.ago12mJoinTotal();
    }
    
    public int ago11mJoinTotal() throws Exception{
    	return admindao.ago11mJoinTotal();
    }
    
    public int ago10mJoinTotal() throws Exception{
    	return admindao.ago10mJoinTotal();
    }
    
    public int ago9mJoinTotal() throws Exception{
    	return admindao.ago9mJoinTotal();
    }
    
    public int ago8mJoinTotal() throws Exception{
    	return admindao.ago8mJoinTotal();
    }

    public int ago7mJoinTotal() throws Exception{
    	return admindao.ago7mJoinTotal();
    }
    
    public int ago6mJoinTotal() throws Exception{
    	return admindao.ago6mJoinTotal();
    }
    
    public int ago5mJoinTotal() throws Exception{
    	return admindao.ago5mJoinTotal();
    }
    
    public int ago4mJoinTotal() throws Exception{
    	return admindao.ago4mJoinTotal();
    }
    
    public int ago3mJoinTotal() throws Exception{
    	return admindao.ago3mJoinTotal();
    }
    
    public int ago2mJoinTotal() throws Exception{
    	return admindao.ago2mJoinTotal();
    }
    
    public int ago1mJoinTotal() throws Exception{
    	return admindao.ago1mJoinTotal();
    }
    
    //전체 회원 목록
    public int countAllUsers() throws Exception{
    	return admindao.countAllUsers();
    }
}

    