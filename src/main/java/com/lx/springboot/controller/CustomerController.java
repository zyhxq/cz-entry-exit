package com.lx.springboot.controller;


import com.lx.springboot.entity.Customer;
import com.lx.springboot.service.CustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping
    @RequiresPermissions("customer:list")
    public String getAllCustomer(Model model){
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        return "customer";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("customer:delete")
    public String deleteCustomer(Integer id){
        customerService.removeById(id);
        return "redirect:/customer";
    }
}
