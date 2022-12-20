package com.laonworks502.team1st.dao.studygroup;

import com.laonworks502.team1st.model.studygroup.StudyGroupBean;

public interface StudyGroupDao {
    int getPostCountByNo(int board_id, int no);

    int createMatching(StudyGroupBean studyBean);

    StudyGroupBean getStudyByNo(int no, String email);

    int countAllMatching(int no, String email);

    int searchMatching(int no, String email);
}


