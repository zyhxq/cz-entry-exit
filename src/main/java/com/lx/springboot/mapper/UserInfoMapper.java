package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 申请人 Mapper 接口
 * </p>
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

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
