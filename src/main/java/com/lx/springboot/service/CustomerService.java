package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.Customer;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 获取所有的会员信息
     *
     * @return
     */
    List<Customer> getAllCustomer();
}
