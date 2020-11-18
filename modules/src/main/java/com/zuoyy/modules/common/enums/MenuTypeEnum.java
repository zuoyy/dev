package com.zuoyy.modules.common.enums;

public enum MenuTypeEnum {

	type1(1,"菜单导航","菜单导航"),
	type2(2,"页面按钮","页面按钮"),
    type3(3,"表格按钮","表格按钮"),
    type4(4,"其它资源","其它资源");

	public final int code;
    public final String name;
    public final String usName;

    MenuTypeEnum(int code, String name, String usName) {
    	this.code = code;
        this.name = name;
        this.usName = usName;
    }
    
    public static MenuTypeEnum getMenuType(Integer code) {
        for(MenuTypeEnum r : MenuTypeEnum.values()) {
            if(r.code==code) {
                return r;
            }
        }
        return null;
    }
    
}
