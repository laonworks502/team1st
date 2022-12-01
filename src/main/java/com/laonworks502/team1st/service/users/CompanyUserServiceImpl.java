package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.dao.users.CompanyUserDao;
import com.laonworks502.team1st.model.company.CompanyBean;
import com.laonworks502.team1st.model.users.CompanyUserBean;
import com.laonworks502.team1st.model.users.UserBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("company")
public class CompanyUserServiceImpl implements CommonUserService {

	@Autowired
	private CompanyUserDao cud;
	
	// 로그인
	@Override
	public String loginUser(UserBean userBean) throws Exception {
		return null;
	}
	
	// 로그아웃
	@Override
	public String logoutUser(UserBean userBean) {
		return null;
	}
	
	// 이메일로 회원 정보 찾기
    public CompanyUserBean getMember(String email) {
    		return cud.getMember(email);
    	}
    
    // 기업 등록
    public int joinCompany(CompanyBean cb) throws Exception {
    	return cud.joinCompany(cb);
    }

    // 기업 회원 가입
    @Override
    public int joinUser(UserBean userBean) throws Exception {
    	return cud.CompanyjoinUser(userBean);
    }

    // 기업 회원 정보 수정
    @Override
    public int amendUser(UserBean userBean) throws Exception {
        return cud.amendUser(userBean);
    }

    // 기업 회원 비밀번호 찾기
    public CompanyUserBean findPasswdUser(UserBean userBean) throws Exception {
		return cud.findPasswdCompanyUser(userBean);
    }

    // 기업 회원 탈퇴
    @Override
    public int quitUser(UserBean ub) throws Exception {
        return cud.quitUser(ub);
    }
    
    // 이메일 중복검사 
    public int emailcheck(String email) throws Exception {
		int cnt = cud.emailcheck(email);
		return cnt;
	}

    // 기업명으로 기업정보 불러오기
	public CompanyBean getCompany(String company_name) throws Exception {
		return cud.getCompany(company_name);
	}

    

}
