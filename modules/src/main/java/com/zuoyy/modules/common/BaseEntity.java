package com.zuoyy.modules.common;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable  {

    /**
     * 实体编号（唯一标识）
     */

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "com.zuoyy.common.uuid.Base64UuidGenerator")
    @Column(name = "id", length = 22)
    protected String id;


}
