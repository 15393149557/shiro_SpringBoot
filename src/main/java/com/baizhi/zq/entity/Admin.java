package com.baizhi.zq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private String adminId;
    private String username;
    private String password;
    private String salt;

    //关系属性 一个用户对应多个角色
    private ArrayList<Role> roles;
}
