package com.lx.springboot.config;

import com.lx.springboot.entity.SysPermission;
import com.lx.springboot.entity.SysRole;
import com.lx.springboot.entity.SysUser;
import com.lx.springboot.service.SysPermissionService;
import com.lx.springboot.service.SysRoleService;
import com.lx.springboot.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName ShiroRealm
 * @Description 自定义安全数据源
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取已经认证通过的用户信息
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        //根据用户id查询角色信息
        List<SysRole> sysRoles = sysRoleService.getSysRoleByUserId(sysUser.getId());
        //函数式编程+lambda表达式
        Set<String> roles = sysRoles.stream().map(SysRole::getRole).collect(Collectors.toSet());
        authorizationInfo.setRoles(roles);
        //根据用户id查询权限信息
        List<SysPermission> sysPermissions = sysPermissionService.getSysPermissionByUserId(sysUser.getId());
        Set<String> permissions = sysPermissions.stream().map(SysPermission::getPermission).collect(Collectors.toSet());
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = (String) token.getPrincipal();
        //根据用户名查询用户信息
        SysUser sysUser = sysUserService.getSysUserByUsername(username);
        //判断用户是否存在
        if (Objects.isNull(sysUser)) {
            throw new UnknownAccountException();
        }
        //判断用户状态是否正常
        if (Objects.equals("2", sysUser.getStatus()) || Objects.equals("3", sysUser.getStatus())) {
            throw new DisabledAccountException();
        }
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
    }
}
