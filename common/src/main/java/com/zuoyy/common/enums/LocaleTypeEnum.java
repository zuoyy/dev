package com.zuoyy.common.enums;

public enum LocaleTypeEnum {

	en_US("en-US","English (US)"),
	zh_CN("zh-CN","简体中文");

	public final String code;
    public final String name;

    LocaleTypeEnum(String code, String name) {
    	this.code = code;
        this.name = name;
    }
    
    public static LocaleTypeEnum getLocaleType(String code) {
        for(LocaleTypeEnum l : LocaleTypeEnum.values()) {
            if(l.code.equals(code)) {
                return l;
            }
        }
        return null;
    }
    
    
}
