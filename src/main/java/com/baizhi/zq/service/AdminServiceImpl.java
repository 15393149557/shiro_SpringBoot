package com.baizhi.zq.service;

import com.baizhi.zq.dao.AdminDao;
import com.baizhi.zq.entity.Admin;
import com.baizhi.zq.service.AdminService;
import com.baizhi.zq.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminDao adminDao;

    @Override
    public Admin queryByUsername(String username) {

        Admin admin = adminDao.queryByUsername(username);

        return admin;
    }

    @Override
    public Admin queryByUsernames(String username) {
        Admin admin = adminDao.queryByUsernames(username);

        return admin;
    }
}
