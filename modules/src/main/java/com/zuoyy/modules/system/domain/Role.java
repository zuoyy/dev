package com.zuoyy.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.modules.common.DataEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "sys_role")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update sys_role" + StatusUtil.sliceDelete)
public class Role extends DataEntity implements Serializable {

    private String name;
    private String title;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<User> users = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    @JsonIgnore
    private Set<Menu> menus = new HashSet<>(0);
}
