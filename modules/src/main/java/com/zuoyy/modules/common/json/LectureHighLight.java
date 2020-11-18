package com.zuoyy.modules.common.json;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LectureHighLight {

    private String title;
    private String highLight;
    private Date startDate;
    private Date endDate;

}
