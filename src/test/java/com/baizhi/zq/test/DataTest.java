package com.baizhi.zq.test;


import com.baizhi.zq.Application;
import com.baizhi.zq.dao.AdminDao;
import com.baizhi.zq.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DataTest {

    @Resource
    AdminDao adminDao;

    @Test
    public void test(){
        Admin admin = adminDao.queryByUsername("xiaohei");

        System.out.println(admin);

        Admin xiaohei = adminDao.queryByUsernames("xiaohei");
        System.out.println(xiaohei);
    }


}
