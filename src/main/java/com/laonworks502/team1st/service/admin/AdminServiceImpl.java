package com.laonworks502.team1st.service.admin;

import com.laonworks502.team1st.dao.admin.AdminDao;
import com.laonworks502.team1st.model.admin.AdminBean;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDao adminDao;
    public AdminBean userCheck(String id) throws Exception{
        return adminDao.userCheck(id);
    }
}
