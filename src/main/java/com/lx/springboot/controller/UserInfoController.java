package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 */
@RestController
@RequestMapping(value = {"/userInfo"})
public class UserInfoController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, value = {"/getAllUserInfo"}, produces = "application/json;charset=UTF-8")
    public String getAllUserInfo(){
        List<Customer> customers = customerService.getAllCustomer();
        return JSONObject.toJSONString(customers);
    }

}
