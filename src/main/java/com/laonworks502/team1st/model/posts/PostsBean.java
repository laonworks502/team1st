package com.laonworks502.team1st.model.posts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostsBean {
    private int no;
    private String board_id;
    private String title;
    private String writer;
    private String content;
    private String date_posted;

}
