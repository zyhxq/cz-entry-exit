package com.lx.springboot.mapper;

import com.lx.springboot.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 获取所有的会员信息
     *
     * @return
     */
    List<Customer> getAllCustomer();
}
