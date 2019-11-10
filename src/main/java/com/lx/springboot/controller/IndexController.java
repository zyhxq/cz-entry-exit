package com.lx.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description 首页控制器
 */
@Controller
public class IndexController {

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
