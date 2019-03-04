package com.mall.app.dao;

import com.mall.app.entity.AppUser;
import com.mall.common.base.dao.BaseDao;
import org.mapstruct.Mapper;

public interface UserDao extends BaseDao<AppUser> {

    AppUser queryByMobile(String mobile);
}

