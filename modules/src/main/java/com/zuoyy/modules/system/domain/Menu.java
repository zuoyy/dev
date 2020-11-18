package com.zuoyy.modules.system.domain;

import com.zuoyy.modules.common.DataEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sys_menu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends DataEntity implements Serializable {

    private String pid;
    private String pids;
    private String title;
    private String usTitle;
    private String url;
    private String perms;
    private String icon;
    private Integer level;
    private Integer type;
    private Integer sort;
    private String btnId;
    private String btnOnclick;
    private String btnClass;

    @Transient
    private List<Menu> children = new ArrayList<>();


    public void setPids(String pids) {
        if (pids.startsWith(",")){
            pids = pids.substring(1);
        }
        this.pids = pids;
    }
}
