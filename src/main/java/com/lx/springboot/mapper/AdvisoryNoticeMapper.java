package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 咨询公告 Mapper 接口
 * </p>
 */
public interface AdvisoryNoticeMapper extends BaseMapper<AdvisoryNotice> {

    int addAdvisoryNotice(AdvisoryNotice advisoryNotice);

    /**
     * 获取所有的申请人信息
     *
     * @return
     */
    List<AdvisoryNotice> getAllAdvisoryNotice();

    List<AdvisoryNotice> getAdvisoryNoticeByParam(AdvisoryNotice advisoryNotice);


    int deleteById(Long id);
}
