package com.zuoyy.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.component.excel.annotation.Excel;
import com.zuoyy.modules.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sys_user")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update sys_user" + StatusUtil.sliceDelete)
@Excel("用户数据")
public class User extends BaseEntity implements Serializable {

    @Excel("登录名")
    private String username;
    private String password;
    private String salt;
    @Excel("用户名")
    private String nickname;
    private String picture;
    @Excel(value = "性别", dict = "USER_SEX")
    private String sex;
    @Excel("手机号码")
    private String phone;
    @Excel("电子邮箱")
    private String email;
    private String language;
    @CreatedDate
    @Excel("创建时间")
    private Date createDate;
    @LastModifiedDate
    @Excel("更新时间")
    private Date updateDate;
    @Excel("备注")
    private String remark;
    @Excel(value = "状态", dict = "DATA_STATUS")
    private Integer status = StatusEnum.OK.code;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="dept_id")
    @JsonIgnore
    private Dept dept;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>(0);

    public boolean hasRole(Role role) {
        if (roles.size()==0) {
            return false;
        }
        return roles.contains(role);
    }

    public boolean hasRole(String roleName) {
        if (roles.size()==0) {
            return false;
        }
        for(Role role:roles){
            if(role.getName().equals(roleName)){
                return true;
            }
        }
        return false;
    }

}
