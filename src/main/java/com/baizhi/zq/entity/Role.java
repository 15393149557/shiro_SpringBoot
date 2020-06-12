package com.baizhi.zq.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String roleId;
    private String roleName;

    //关系属性 一个角色对应多个权限
    private ArrayList<Authority> authorities;
}
