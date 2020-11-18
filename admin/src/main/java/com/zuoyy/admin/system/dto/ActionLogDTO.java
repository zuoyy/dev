package com.zuoyy.admin.system.dto;

import com.zuoyy.modules.system.domain.ActionLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionLogDTO extends ActionLog {

    private String logType;


}
