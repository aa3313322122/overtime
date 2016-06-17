package com.puban.overtime.authority.shiro;

import com.puban.overtime.authority.model.Permission;
import com.puban.overtime.authority.model.Role;
import com.puban.overtime.authority.model.User;
import com.puban.overtime.authority.service.IUserRoleService;
import com.puban.overtime.authority.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by yongsama on 2016/5/18.
 * 自定义的拦截器需要继承AuthorizingRealm并实现登录验证和赋予角色权限的两个方法，进行数据源配置
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取登录时输入的用户名
        String loginName = (String) principalCollection.getPrimaryPrincipal();
        // 到数据库查是否有此对象
        User user = userService.getUserInfoByName(loginName);
        if (null != user) {
            if (user.getFdId() == 1) {// 超级管理员 始终拥有所有权限
                info.addStringPermission("*");
            }
            List<Role> roles = userRoleService.findRoleByUserId(user.getFdId());
            if(null != roles){
                HashSet hs = new LinkedHashSet();
                for (Role role : roles) {
                    String roleKey = role.getFdRoleKey();
                    hs.add(roleKey);
                    // 角色对应相应的权限
                    List<Permission> res = role.getPermissions();
                    if (null != res) {
                        for (Permission permission : res) {
                            info.addStringPermission(permission.getFdPermissionKey());
                        }
                    }
                }
                info.setRoles(hs);
            }
        }
        return info;
    }

    /**
     * 登陆验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //UsernamePasswordToken令牌类用来存放提交的登录信息,实现了 AuthenticationToken 接口，它提供了一种获得凭证和用户的主体（帐户身份）的方式。
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String password = new String((char[]) token.getCredentials());
        User userInfo = userService.getUserInfoByName(usernamePasswordToken.getUsername());  //查出是否有此用户
        if (null == userInfo.getFdId()) {
            throw new UnknownAccountException("用户帐号不存在！");
        }
        if (!userInfo.getFdPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }
        //存在，将此用户存放到登录认证info中,但不能把整个user对象放入，不然会出现错误数组下标越界，在项目中user对象信息过于庞大，不能全部存入Cookie
        return new SimpleAuthenticationInfo(userInfo.getFdUserName(), userInfo.getFdPassword(), getName());
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

}
