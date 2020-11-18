package com.zuoyy.devtools.generate.domain;

import lombok.Data;

import java.util.List;

/**
 * @author zuo
 */
@Data
public class Field {
    private String name;
    private String title;
    private Integer type;
    private boolean show;

    public Field() {
    }

    public Field(String name, String title, int type,boolean show) {
        this.name = name;
        this.title = title;
        this.type = type;
        this.show = show;
    }
}
