package com.lx.springboot.service.impl;

import com.lx.springboot.entity.SysPermission;
import com.lx.springboot.mapper.SysPermissionMapper;
import com.lx.springboot.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> getSysPermissionByUserId(Integer userId) {
        return baseMapper.getSysPermissionByUserId(userId);
    }
}
