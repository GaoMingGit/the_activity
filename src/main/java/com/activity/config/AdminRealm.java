package com.activity.config;

import com.activity.domain.Admin;
import com.activity.mapper.UserMapper;
import com.activity.service.ActivityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 高铭
 * @date 2019/9/12 - 13:27
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    /**
     *  授权验证逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //查询数据库
        Admin admin = userMapper.findAdminByName(token.getUsername());
        if(admin == null){
            //用户名不存在 直接返回null，shiro底层会抛出UnKnowAccountException异常
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo("",admin.getAdminpassword(),"");
    }
}
