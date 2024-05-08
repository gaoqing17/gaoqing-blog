package com.gaoqing.gaoqingblog.config;

import com.gaoqing.gaoqingblog.pojo.User;
import com.gaoqing.gaoqingblog.service.admin.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class userRealm extends AuthorizingRealm {
    @Autowired
    UserService userservic;

    //设置加密长度
    Map<String,String> userMap = new HashMap<>(16);
    {
        userMap.put("Mark","283538989cef48f3d7d8a1c1bdf2008f");

        super.setName("customRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        //授予账号权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();//通过subject拿到账号权限
        User user = (User) subject.getPrincipal();
        info.addStringPermission(user.getSalt());
        return info;//返回账号权
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>授权doGetAuthenticationInfo");
        UsernamePasswordToken Usertoken = (UsernamePasswordToken) token;
        User user = (User) userservic.selectDao(Usertoken.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        }

        Object principal = user;

        Object credentials = user.getPassword();

        String realmName = getName();

        return new SimpleAuthenticationInfo(principal,credentials,realmName);
    }
}
