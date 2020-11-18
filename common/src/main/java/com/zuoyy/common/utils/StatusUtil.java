package com.zuoyy.common.utils;

import com.zuoyy.common.constant.StatusConst;
import com.zuoyy.common.enums.ResultEnum;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.exception.ResultException;

/**
 * 数据状态工具
 * @author zuo
 */
public class StatusUtil {

    // 逻辑删除语句
    public static final String sliceDelete = " set status=" + StatusConst.DELETE + " WHERE id=?";

    /**
     * 获取状态StatusEnum对象
     * @param param 状态字符参数
     */
    public static StatusEnum getStatusEnum(String param){
        try {
            return StatusEnum.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResultException(ResultEnum.STATUS_ERROR);
        }
    }
}
