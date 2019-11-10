package com.lx.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级权限id
     */
    private Integer parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限字符串，如employees:create,employees:update,employees:delete
     */
    private String permission;

    /**
     * 权限类型：0-目录；1-菜单；2-按钮
     */
    private String type;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 状态：0-不可用；1-可用
     */
    private String status;


}
