package com.laonworks502.team1st.service.admin;


import com.laonworks502.team1st.dao.admin.AdminDao;
import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.model.company.CompanyBean;
import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("admin")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao admindao;

    // 관리자 로그인
    @Override
    public AdminBean getAdminInfo(String id) throws Exception {
        return admindao.getAdminInfo(id);
    }
       
    //일반회원 일별 가입자 수
    @Override
    public int joinTotalDays(int i) throws Exception{
    	return admindao.joinTotalDays(i);
    }
    
    //일반회원 주별 가입자 수
    @Override
    public int joinTotalWeeks(int i) throws Exception{
    	return admindao.joinTotalWeeks(i);
    }
    
    //일반회원 월별 가입자 수
    @Override
    public int joinTotalMonths(int i) throws Exception{
    	return admindao.joinTotalMonths(i);
    }
    
    //기업회원 일별 가입자 수
    @Override
    public int companyJoinChartDate(int i) throws Exception{
    	return admindao.companyJoinChartDate(i);
    }
    
    //기업회원 주별 가입자 수
    @Override
    public int companyJoinChartWeek(int i) throws Exception{
    	return admindao.companyJoinChartWeek(i);
    }
    
    //기업회원 월별 가입자 수
    @Override
    public int companyJoinChartMonth(int i) throws Exception{
    	return admindao.companyJoinChartMonth(i);
    }

    //generalusers - 전체 일반 회원 수 구하기
    @Override
    public int countAllUsers() throws Exception{
        return admindao.countAllUsers();
    }

    //generaluserslist
    @Override
    public List<GeneralUserBean> generalUsersList(Integer page) throws Exception{
        return admindao.generalUsersList(page);
    }

    //companyusers - 전체 기업 회원 수 구하기
    @Override
    public int countAllCompanyUsers() throws Exception{
        return admindao.countAllCompanyUsers();
    }

    //companyluserlist
    @Override
    public List<CompanyUserBean> companyUsersList(Integer page) throws Exception{
        return admindao.companyUsersList(page);
    }

    //companies - 전체 기업 수 구하기
    @Override
    public int countAllCompanies() throws Exception{
        return admindao.countAllCompanies();
    }

    //companylist
    @Override
    public List<CompanyBean> companyList(Integer page) throws Exception{
        return admindao.companyList(page);
    }

    //일반 회원 삭제
    @Override
    public void generalUserDelete(GeneralUserBean email) throws Exception {
        admindao.generalUserDelete(email);
    }

}

    