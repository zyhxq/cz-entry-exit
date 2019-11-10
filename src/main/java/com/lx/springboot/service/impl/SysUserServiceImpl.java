package com.lx.springboot.service.impl;

import com.lx.springboot.entity.SysUser;
import com.lx.springboot.mapper.SysUserMapper;
import com.lx.springboot.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getSysUserByUsername(String username) {
        return baseMapper.getSysUserByUsername(username);
    }
}
