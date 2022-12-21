package com.laonworks502.team1st.service.users;


import com.laonworks502.team1st.SHA256Util;
import com.laonworks502.team1st.dao.users.GeneralUserDao;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int amendUser(UserBean userBean) throws Exception {
        return gud.updateGeneraluser(userBean);
    }


    @Override
    public int quitUser(UserBean userBean) throws Exception {
        return gud.deleteGeneraluser(userBean);
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

    public int resumeupload(GeneralUserBean gub) throws Exception{
        return gud.resumeupload(gub);
    }


    public void addGeneralUser(GeneralUserBean gub) throws Exception{
//        String salt = SHA256Util.generateSalt();
//        gub.setSalt(salt);
//
//        String passwd = gub.getPasswd();
//        passwd = SHA256Util.getEncrypt(passwd, salt);
//
//        gub.setPasswd(passwd);
//        위 과정들이 컨트롤러에 있거나 service메소드에 있거나.
        gud.addGeneralUser(gub);
    }

    public int countAllGeneralPosts(String writer) throws Exception{

        return gud.countAllGeneralPosts(writer);
    }

    public List<PostBean> getGeneralBoardList(@Param("writer") String writer, @Param("startPostNo") int startPostNo,
                                      @Param("PAGES_COUNT") int pages_COUNT) throws Exception{
        return gud.getGeneralBoardList(writer, startPostNo, pages_COUNT);
    }
}
