package com.laonworks502.team1st.service.admin;

import com.laonworks502.team1st.dao.admin.AdminDao;
import com.laonworks502.team1st.model.admin.AdminBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {

    @Autowired
    private AdminDao adminDao;

    public AdminBean getAdminInfo (String adminId) throws Exception{

        return adminDao.getAdminInfo(adminId);
    }
}