package com.mall.forum.service;

import com.mall.forum.modle.User;

/**
 * Created by cuilidong on 2019/2/10.
 */
public interface UserService {

    public User validateLogin(String username, String passwrod);

    public String generateAutoLoginUserHash(String securityHash);

    public String generateAutoLoginSecurityHash(int userId);
}
