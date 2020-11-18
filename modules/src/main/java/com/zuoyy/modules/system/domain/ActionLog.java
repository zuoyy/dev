package com.zuoyy.modules.system.domain;

import com.zuoyy.modules.common.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="sys_action_log")
@EntityListeners(AuditingEntityListener.class)
public class ActionLog extends BaseEntity implements Serializable {

    private String name;
    private Integer type;
    private String ipaddr;
    private String clazz;
    private String method;
    private String model;
    private String recordId;
    @Lob @Column(columnDefinition="TEXT")
    private String message;
    @CreatedDate
    private Date createDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name="oper_by")
    private User operBy;

    public ActionLog(){}
    /**
     * 封装日志对象
     * @param name 日志名称
     * @param message 日志消息
     */
    public ActionLog(String name, String message){
        this.name = name;
        this.message = message;
    }
}
