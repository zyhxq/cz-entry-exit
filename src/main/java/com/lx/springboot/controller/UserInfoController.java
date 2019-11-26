package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 申请人 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping(value = {"/userInfo"})
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/addUserInfo"})
    @ResponseBody
    public String addUserInfo(@RequestBody UserInfo userInfo){
        JSONObject json=new JSONObject();
        try{
            log.info("UserInfoController addUserInfo userInfo:"+JSONObject.toJSONString(userInfo));
            Integer i = userInfoService.addUserInfo(userInfo);
            json.put("id",i);
        }catch(Exception e){
            json.put("success",false);
            log.error("UserInfoController addUserInfo is error",e);
        }
        json.put("success",true);
        return json.toJSONString();
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/getAllUserInfo"})
    @ResponseBody
    public String getAllUserInfo(){
        List<UserInfo> userInfoList=null;
        try{
            log.info("UserInfoController getAllUserInfo start");
            userInfoList = userInfoService.getAllUserInfo();
        }catch(Exception e){
            log.error("UserInfoController getAllUserInfo is error",e);
        }
        return JSONObject.toJSONString(userInfoList);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/getUserInfoByParam"})
    @ResponseBody
    public List<UserInfo> getUserInfoByParam(@RequestBody UserInfo userInfo){
        List<UserInfo> userInfoList=null;
        try{
            log.info("UserInfoController getUserInfoByParam start userInfo:"+JSONObject.toJSONString(userInfo));
            userInfoList = userInfoService.getUserInfoByParam(userInfo);
        }catch(Exception e){
            log.error("UserInfoController getUserInfoByParam is error userInfo:"+JSONObject.toJSONString(userInfo),e);
        }
        log.info("UserInfoController getUserInfoByParam is success");
        return userInfoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/deleteById"})
    @ResponseBody
    public String deleteById(Long id){
        try{
            log.info("UserInfoController deleteById start id:"+id);
            Integer i = userInfoService.deleteById(id);
        }catch(Exception e){
            log.error("UserInfoController deleteById is error id:"+id,e);
        }
        return "success";
    }

}
