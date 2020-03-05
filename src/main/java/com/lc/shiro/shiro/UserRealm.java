package com.lc.shiro.shiro;

import com.lc.shiro.domain.User;
import com.lc.shiro.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");

//        给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//      添加资源的授权字符串
//        info.addStringPermission("user:add");

//        获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();

        User user1=userService.findById(user.getId());

//      添加资源的授权字符串
        String s= user1.getPerms();
        String[] split = s.split(",");
        for (int i = 0; i <split.length; i++) {
            info.addStringPermission(split[i]);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证");

        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;

        User user = userService.findByName(token.getUsername());

        if (user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
