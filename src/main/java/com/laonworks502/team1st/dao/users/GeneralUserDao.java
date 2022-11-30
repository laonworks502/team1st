package com.laonworks502.team1st.dao.users;

import com.laonworks502.team1st.model.users.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper
public interface GeneralUserDao {

    String loginUser(UserBean userBean) throws Exception;

    String logoutUser(UserBean userBean) throws Exception;

    int joinGeneraluser(UserBean userBean) throws Exception;

    int amendUser(UserBean userBean) throws Exception;

    String findPasswdUser(UserBean userBean) throws Exception;

    String quitUser(UserBean userBean) throws Exception;

    int emailDuplicatecheck(String email) throws Exception;

}
