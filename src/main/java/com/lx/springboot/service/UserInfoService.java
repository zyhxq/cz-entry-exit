package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 申请人 服务类
 * </p>
 */
public interface UserInfoService extends IService<UserInfo> {

    int addUserInfo(UserInfo userInfo);

    /**
     * 获取所有的申请人信息
     *
     * @return
     */
    List<UserInfo> getAllUserInfo();

    List<UserInfo> getUserInfoByParam(UserInfo userInfo);

    int deleteById(Long id);
}
