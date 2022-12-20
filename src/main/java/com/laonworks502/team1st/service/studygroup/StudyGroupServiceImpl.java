package com.laonworks502.team1st.service.studygroup;


import com.laonworks502.team1st.dao.studygroup.StudyGroupDao;
import com.laonworks502.team1st.model.studygroup.StudyGroupBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyGroupServiceImpl implements StudyGroupService {

    @Autowired
    private StudyGroupDao studyGroupDao;

    @Override
    public int getPostCountByNo(int board_id, int no) throws Exception {
        return studyGroupDao.getPostCountByNo(board_id, no);
    }

    @Override
    public int createMatching(StudyGroupBean studyBean) throws Exception {
        return studyGroupDao.createMatching(studyBean);
    }

    @Override
    public StudyGroupBean getStudyByNo(int no, String email) throws Exception {
        return studyGroupDao.getStudyByNo(no, email);
    }

    @Override
    public int countAllMatching(int no, String email) {
        return studyGroupDao.countAllMatching(no, email);
    }

    @Override
    public int searchMatching(int no, String email) {
        return studyGroupDao.searchMatching(no, email);
    }

}
