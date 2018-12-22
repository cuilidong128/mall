package com.mall.app.service;

import com.mall.app.entity.AppUser;

import java.util.List;
import java.util.Map;

public interface UserService {

    AppUser queryObject(Long userId);

    List<AppUser> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(String mobile, String password);

    void update(AppUser user);

    void delete(Long userId);

    void deleteBatch(Long[] userIds);

    AppUser queryByMobile(String mobile);

    /**
     * 用户登录
     * @param mobile    手机号
     * @param password  密码
     * @return          返回用户ID
     */
    long login(String mobile, String password);
}
