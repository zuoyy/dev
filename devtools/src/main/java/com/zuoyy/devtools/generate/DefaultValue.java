package com.zuoyy.devtools.generate;

import com.zuoyy.common.utils.ToolUtil;
import com.zuoyy.devtools.generate.domain.Basic;
import com.zuoyy.devtools.generate.domain.Field;
import com.zuoyy.devtools.generate.enums.FieldType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zuo
 */
public class DefaultValue {

    /**
     * 基本信息
     */
    public static Basic getBasic(){
        Basic basic = new Basic();
        basic.setProjectPath(ToolUtil.getProjectPath() + "/");
        basic.setPackagePath("com.zuoyy");
        basic.setAuthor("zuo");
        basic.setGenModule("system");
        basic.setTablePrefix("b_");
        return basic;
    }

    /**
     * 字段列表
     */
    public static List<Field> fieldList(){
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("title", "标题", FieldType.String.getCode(),true));
        fields.add(new Field("type", "类型", FieldType.Integer.getCode(),true));
        return fields;
    }
}
