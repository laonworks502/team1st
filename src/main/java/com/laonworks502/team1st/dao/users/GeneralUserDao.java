package com.laonworks502.team1st.dao.users;

import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper
public interface GeneralUserDao {

    String loginUser(UserBean userBean) throws Exception;

    String logoutUser(UserBean userBean) throws Exception;

    int joinGeneraluser(UserBean userBean) throws Exception;

    int amendUser(UserBean userBean) throws Exception;

    String findPasswdUser(UserBean userBean) throws Exception;

    int quitUser(UserBean userBean) throws Exception;

    int emailDuplicatecheck(String email) throws Exception;

    GeneralUserBean checkGeneraluser(String email) throws Exception;

    int updateGeneraluser(UserBean userBean) throws Exception;

    int deleteGeneraluser(UserBean userBean) throws Exception;

    int resumeupload(UserBean userBean) throws Exception;

    int addGeneralUser(GeneralUserBean gub) throws Exception;

    int countAllGeneralPosts(String writer) throws Exception;

    List<PostBean> getGeneralBoardList(@Param("writer") String writer, @Param("startPostNo") int startPostNo,
                                              @Param("PAGES_COUNT") int pages_COUNT) throws Exception;
}
