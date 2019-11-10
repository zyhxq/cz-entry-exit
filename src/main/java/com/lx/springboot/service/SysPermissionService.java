package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户id查询权限信息
     *
     * @param userId
     * @return
     */
    List<SysPermission> getSysPermissionByUserId(Integer userId);
}
