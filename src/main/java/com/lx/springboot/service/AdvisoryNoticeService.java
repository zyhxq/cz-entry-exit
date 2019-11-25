package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.AdvisoryNotice;

import java.util.List;

/**
 * <p>
 * 咨询公告 服务类
 * </p>
 */
public interface AdvisoryNoticeService extends IService<AdvisoryNotice> {

    int addAdvisoryNotice(AdvisoryNotice advisoryNotice);

    List<AdvisoryNotice> getAllAdvisoryNotice();

    List<AdvisoryNotice> getAdvisoryNoticeByParam(AdvisoryNotice advisoryNotice);

    int deleteById(Long id);
}
