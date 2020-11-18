package com.zuoyy.modules.system.domain;

import com.zuoyy.modules.common.DataEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name="sys_dept")
@EntityListeners(AuditingEntityListener.class)
public class Dept extends DataEntity implements Serializable {

	// 部门名称
	private String title;
	// 父级编号
	private String pid;
	// 所有父级编号
	private String pids;
	// 排序
	private Integer sort;

}
