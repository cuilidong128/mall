package com.mall.system.service.impl;

import com.mall.common.annotation.BaseService;
import com.mall.common.base.service.impl.BaseServiceImpl;
import com.mall.system.dao.UpmsUserMapper;
import com.mall.system.model.UpmsUser;
import com.mall.system.model.UpmsUserExample;
import com.mall.system.service.UpmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UpmsUserService实现
 * Created by shuzheng on 2017/3/20.
 */

//
@Service
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

//    @Override
//    public UpmsUser selectByPrimaryKey(Integer userId) {
//
//        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(userId);
//
//        return upmsUser;
//    }


}
