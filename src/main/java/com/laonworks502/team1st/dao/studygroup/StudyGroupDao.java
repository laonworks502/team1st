package com.laonworks502.team1st.dao.studygroup;

import com.laonworks502.team1st.model.studygroup.StudyGroupBean;
import com.laonworks502.team1st.model.studygroup.StudyListBean;

import java.util.List;

public interface StudyGroupDao {
    int getPostCountByNo(int board_id, int no);

    int createMatching(StudyGroupBean studyBean);

    StudyGroupBean getStudyByNo(int no, String email);

    int countAllMatching(int no, String email);

    int searchMatching(int no, String email);

    List<StudyListBean> getStudyList(String email);

    int deleteStudy(int no, String email);
}


