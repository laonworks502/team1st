package com.laonworks502.team1st.model.scraps;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ScrapsBean {
    private String user_email;
    private String scrap_list;
    private Date date_scraped;

}
