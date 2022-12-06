package com.laonworks502.team1st.service.admin;


import com.laonworks502.team1st.dao.admin.AdminDao;
import com.laonworks502.team1st.model.admin.AdminBean;

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
    
    //일별 가입자 수
    public int joinTotalDays(int i) throws Exception{
    	return admindao.joinTotalDays(i);
    }
    
    //주별 가입자 수
    public int joinTotalWeeks(int i) throws Exception{
    	return admindao.joinTotalWeeks(i);
    }
    
    //월별 가입자 수
    public int joinTotalMonths(int i) throws Exception{
    	return admindao.joinTotalMonths(i);
    }
    
    //전체 회원 목록
    public int countAllUsers() throws Exception{
    	return admindao.countAllUsers();
    }
}

    