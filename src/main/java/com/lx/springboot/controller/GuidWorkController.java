package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 咨询公告 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping(value = {"/advisoryNotice"})
public class AdvisoryNoticeController {

    @Autowired
    private AdvisoryNoticeService advisoryNoticeService;

    @RequestMapping(value = {"/queryNotice"})
    @ResponseBody
    public List<AdvisoryNotice> queryNotice(@RequestBody AdvisoryNotice advisoryNotice){
        List<AdvisoryNotice> noticeList=new ArrayList<AdvisoryNotice>();
        try{
            log.info("AdvisoryNoticeController queryNotice advisoryNotice:"+JSONObject.toJSONString(advisoryNotice));
            noticeList=advisoryNoticeService.getAllAdvisoryNotice();
        }catch(Exception e){
            log.error("AdvisoryNoticeController queryNotice is error",e);
        }
        return noticeList;
    }


}
