package com.baizhi.zq.realm;

import com.baizhi.zq.entity.Admin;
import com.baizhi.zq.entity.Authority;
import com.baizhi.zq.entity.Role;
import com.baizhi.zq.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

public class Realm extends AuthorizingRealm {

    @Resource
    AdminService adminService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");

        //1.查询用户有哪些权限
        String username = (String)principalCollection.getPrimaryPrincipal();

        //  根据用户主身份去查询该用户有哪些角色
        // admin sadmin supers
        //  根据用户主身份去查询角色对应有哪些权限

        Admin admin = adminService.queryByUsernames(username);
        //角色集合
        ArrayList<String> roleList = new ArrayList<>();
        //权限集合
        ArrayList<String> PermissionList = new ArrayList<>();

        //获取所有的角色
        ArrayList<Role> roles = admin.getRoles();
        //遍历角色
        for (Role role : roles) {
            //获取角色名称
            String roleName = role.getRoleName();
            //将角色放入角色集合
            roleList.add(roleName);

            //获取权限集合
            ArrayList<Authority> authorities = role.getAuthorities();

            //遍历权限集合
            for (Authority authority : authorities) {

                //获取权限名称
                String authorityName = authority.getAuthorityName();

                //将权限放入权限集合
                PermissionList.add(authorityName);
            }
        }

        //2.授予相关权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //授予角色
        //info.addRole("admin");
        //授予角色集合
        info.addRoles(roleList);

        //授予权限
        //info.addStringPermission("user:query");
        //授予权限集合
        info.addStringPermissions(PermissionList);


        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");

        //获取身份信息
        String username = (String)token.getPrincipal();

        //根据用户名查询用户
        Admin admin = adminService.queryByUsername(username);

        //将数据库查询到的数据封装到info中  密文数据
        AuthenticationInfo info= new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),ByteSource.Util.bytes(admin.getSalt()),this.getName());
        /*if(username.equals("xiaohei")){
            info = new SimpleAuthenticationInfo("xiaohei","e0504e77b06f5a26faf37d044c65992b", ByteSource.Util.bytes("ABCD"),this.getName());
        }*/
        return info;
    }
}