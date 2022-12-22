package com.laonworks502.team1st.service.studygroup;

import com.laonworks502.team1st.model.studygroup.StudyGroupBean;
import com.laonworks502.team1st.model.studygroup.StudyListBean;

import java.util.List;

public interface StudyGroupService {
    /*[매칭]*/

    // 글 존재 여부 검색
    int getPostCountByNo(int board_id, int no) throws Exception;

    // 매칭 생성
    int createMatching(StudyGroupBean studyBean) throws Exception;

    // 매칭 정보 검색
    StudyGroupBean getStudyByNo(int no, String email) throws Exception;

    // 매칭 인원 수 검색
    int countAllMatching(int no, String email);

    // 매칭 가능 유무 검색
    int searchMatching(int no, String email);

    // 매칭 정보 전체 검색
    List<StudyListBean> getStudyList(String email);

    // 참여한 매칭 삭제
    int deleteStudy(int no, String email);
}
