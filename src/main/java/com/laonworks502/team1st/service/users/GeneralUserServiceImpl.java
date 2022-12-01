package com.laonworks502.team1st.service.users;


import com.laonworks502.team1st.dao.users.GeneralUserDao;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("general")
public class GeneralUserServiceImpl implements CommonUserService {

    @Autowired
    private GeneralUserDao gud;

    @Override
    public String loginUser(UserBean userBean) {
        return null;
    }

    @Override
    public String logoutUser(UserBean userBean) {
        return null;
    }

    @Override
    public int joinUser(UserBean userBean) throws Exception {
        return gud.joinGeneraluser(userBean);

    }

    @Override
    public String amendUser(UserBean userBean) {
        return null;
    }

    @Override
    public String findPasswdUser(UserBean userBean) {
        return null;
    }

    @Override
    public String quitUser(UserBean userBean) {
        return null;
    }


    public int emailDuplicatecheck(String email) throws Exception{
        return gud.emailDuplicatecheck(email);
    }

    public GeneralUserBean checkGeneraluser(String email) throws Exception{
        return gud.checkGeneraluser(email);
    }

    public int updateGeneraluser(GeneralUserBean gub) throws Exception{
        return gud.updateGeneraluser(gub);
    }

    public int deleteGeneraluser(GeneralUserBean gub) throws Exception{
        return gud.deleteGeneraluser(gub);
    }

}
