package com.baizhi.zq.service;

import com.baizhi.zq.entity.Admin;

public interface AdminService {

    Admin queryByUsername(String username);
    Admin queryByUsernames(String username);

}
