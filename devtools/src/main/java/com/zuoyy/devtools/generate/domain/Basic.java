package com.zuoyy.devtools.generate.domain;

import lombok.Data;

/**
 * @author zuo
 */
@Data
public class Basic {
    private String projectPath;
    private String packagePath;
    private String author;
    private String genTitle;
    private String genModule;
    private String genGroup;
    private String genPMenu;
    private String tablePrefix;
    private String tableName;
    private String tableEntity;
    private String requestMapping;
    private Integer moduleType;
}
