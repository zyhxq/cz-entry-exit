package com.lx.springboot.service.impl;

import com.lx.springboot.entity.SysRole;
import com.lx.springboot.mapper.SysRoleMapper;
import com.lx.springboot.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> getSysRoleByUserId(Integer userId) {
        return baseMapper.getSysRoleByUserId(userId);
    }
}
