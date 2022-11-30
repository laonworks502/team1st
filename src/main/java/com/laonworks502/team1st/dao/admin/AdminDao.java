package com.laonworks502.team1st.dao.admin;

import com.laonworks502.team1st.model.admin.AdminBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    public AdminBean getAdminInfo (String adminId) throws Exception;
}
