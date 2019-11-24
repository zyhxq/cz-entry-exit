package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.mapper.UserInfoMapper;
import com.lx.springboot.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 申请人 服务实现类
 * </p>
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return baseMapper.addUserInfo(userInfo);
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        return baseMapper.getAllUserInfo();
    }

    @Override
    public List<UserInfo> getUserInfoByParam(UserInfo userInfo) {
        return baseMapper.getUserInfoByParam(userInfo);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }
}
