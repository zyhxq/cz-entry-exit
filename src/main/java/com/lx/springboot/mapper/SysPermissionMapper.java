package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户id查询权限信息
     *
     * @param userId
     * @return
     */
    List<SysPermission> getSysPermissionByUserId(Integer userId);
}
