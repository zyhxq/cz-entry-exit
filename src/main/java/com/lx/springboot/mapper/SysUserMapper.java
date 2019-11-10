package com.lx.springboot.mapper;

import com.lx.springboot.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    SysUser getSysUserByUsername(String username);
}
