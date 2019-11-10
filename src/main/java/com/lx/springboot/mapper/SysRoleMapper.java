package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> getSysRoleByUserId(Integer userId);
}
