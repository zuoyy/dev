package com.zuoyy.modules.system.domain;

import com.zuoyy.modules.common.DataEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="sys_dict")
@EntityListeners(AuditingEntityListener.class)
public class Dict extends DataEntity implements Serializable {

    private String name;
    private String title;
    private Integer type;
    @Lob @Column(columnDefinition="TEXT")
    private String value;


}
