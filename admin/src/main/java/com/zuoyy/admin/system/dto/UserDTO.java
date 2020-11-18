package com.zuoyy.admin.system.dto;

import com.zuoyy.modules.system.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends User {

    private String roleName;


}
