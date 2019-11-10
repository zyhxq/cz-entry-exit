package com.lx.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员编号
     */
    private String customerId;

    /**
     * 会员名称
     */
    private String customerName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态：1-正常；2-冻结；3-不可用
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime updatedDate;


}
