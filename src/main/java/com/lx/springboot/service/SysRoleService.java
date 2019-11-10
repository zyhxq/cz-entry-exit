package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据用户id查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> getSysRoleByUserId(Integer userId);
}
