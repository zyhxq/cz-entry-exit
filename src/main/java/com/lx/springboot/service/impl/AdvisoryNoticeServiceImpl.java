package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.mapper.AdvisoryNoticeMapper;
import com.lx.springboot.mapper.UserInfoMapper;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 咨询公告 服务实现类
 * </p>
 */
@Service
public class AdvisoryNoticeServiceImpl extends ServiceImpl<AdvisoryNoticeMapper, AdvisoryNotice> implements AdvisoryNoticeService {

    @Override
    public int addAdvisoryNotice(AdvisoryNotice advisoryNotice) {
        return baseMapper.addAdvisoryNotice(advisoryNotice);
    }

    @Override
    public List<AdvisoryNotice> getAllAdvisoryNotice() {
        return baseMapper.getAllAdvisoryNotice();
    }

    @Override
    public List<AdvisoryNotice> getAdvisoryNoticeByParam(AdvisoryNotice advisoryNotice) {
        return baseMapper.getAdvisoryNoticeByParam(advisoryNotice);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }
}
