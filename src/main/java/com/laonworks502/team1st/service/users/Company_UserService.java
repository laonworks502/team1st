package com.laonworks502.team1st.service.users;

import com.laonworks502.team1st.model.users.Company_UserBean;

public interface Company_UserService extends CommonUserService {

    // 기업 회원 가입
    public String joinCompany_User(Company_UserBean company_userBean);

    // 기업 회원 수정
    public String amendCompany_User(Company_UserBean company_userBean);

    // 기업 회원 비밀번호 찾기
    public String findPasswdCompany_User(Company_UserBean company_userBean);

    // 기업 회원 탈퇴
    String quitCompany_User(String email, String exit_reason);

}
