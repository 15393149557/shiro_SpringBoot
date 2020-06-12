package com.baizhi.zq.dao;


import com.baizhi.zq.entity.Admin;

public interface AdminDao extends BasicDao<Admin>{

    Admin queryByUsername(String username);

    Admin queryByUsernames(String username);
}
