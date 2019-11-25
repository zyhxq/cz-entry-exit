package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.AdvisoryNoticeService;
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
@RequestMapping(value = {"/advisoryNotice"})
public class AdvisoryNoticeController {

    @Autowired
    private AdvisoryNoticeService advisoryNoticeService;

    @RequestMapping(value = {"/addNotice"})
    @ResponseBody
    public String addAdvisoryNotice(@RequestBody AdvisoryNotice advisoryNotice){
        try{
            log.info("AdvisoryNoticeController.addAdvisoryNotice advisoryNotice:"+JSONObject.toJSONString(advisoryNotice));
            Integer i = advisoryNoticeService.addAdvisoryNotice(advisoryNotice);
        }catch(Exception e){
            log.error("UserInfoController addUserInfo is error",e);
        }
        return "success";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/getAllAdvisoryNotice"})
    @ResponseBody
    public String getAllAdvisoryNotice(){
        List<AdvisoryNotice> advisoryNoticeList=null;
        try{
            log.info("AdvisoryNoticeController.addAdvisoryNotice start");
            advisoryNoticeList = advisoryNoticeService.getAllAdvisoryNotice();
        }catch(Exception e){
            log.error("AdvisoryNoticeController.addAdvisoryNotice is error",e);
        }
        return JSONObject.toJSONString(advisoryNoticeList);
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/getAdvisoryNoticeByParam"})
    @ResponseBody
    public List<AdvisoryNotice> getAdvisoryNoticeByParam(@RequestBody AdvisoryNotice advisoryNotice){
        List<AdvisoryNotice> advisoryNoticeList=null;
        try{
            log.info("AdvisoryNoticeController.getAdvisoryNoticeByParam start advisoryNotice:"+JSONObject.toJSONString(advisoryNotice));
            advisoryNotice.setStart(0);
            advisoryNotice.setEnd(5);
            advisoryNotice.setType(TypeEnum.NOTICE.getModelType());
            advisoryNoticeList = advisoryNoticeService.getAdvisoryNoticeByParam(advisoryNotice);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.getAdvisoryNoticeByParam is error userInfo:"+JSONObject.toJSONString(advisoryNotice),e);
        }
        log.info("AdvisoryNoticeController.getAdvisoryNoticeByParam is success");
        return advisoryNoticeList;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/deleteById"})
    @ResponseBody
    public String deleteById(Long id){
        try{
            log.info("AdvisoryNoticeController deleteById start id:"+id);
            Integer i = advisoryNoticeService.deleteById(id);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.deleteById is error id:"+id,e);
        }
        return "success";
    }

}
