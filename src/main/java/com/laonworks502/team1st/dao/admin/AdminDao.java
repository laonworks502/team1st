package com.laonworks502.team1st.dao.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import com.laonworks502.team1st.service.admin.AdminService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    @Autowired
    private SqlSession sqlSession;

    public AdminBean userCheck(String id) throws Exception {
        return (AdminBean) sqlSession.selectOne("adminlogin_check", id);
    }
}
