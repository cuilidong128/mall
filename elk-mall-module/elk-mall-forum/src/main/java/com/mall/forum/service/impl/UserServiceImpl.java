package com.mall.forum.service.impl;

import com.mall.forum.dao.ConfigDao;
import com.mall.forum.dao.UserDao;
import com.mall.forum.modle.User;
import com.mall.forum.service.UserService;
import com.mall.forum.util.ConfigKeys;
import com.mall.forum.util.JForumConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mall.forum.util.MD5;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JForumConfig config;

    public User validateLogin(String username,String passwrod){

        return  userDao.validateLogin(username, MD5.hash(passwrod));
    }


    /**
     * Create a security hash to be used as extra security for auto logins.
     * @param userId the id of the user to generate the hash
     * @return the hash
     */
    public String generateAutoLoginSecurityHash(int userId) {
        String systemHash = MD5.hash(this.config.getValue(ConfigKeys.USER_HASH_SEQUENCE) + userId);
        return MD5.hash(System.currentTimeMillis() + systemHash);
    }

    /**
     * Generate a hash based on the security hash of an user
     * @param securityHash the user's current security hash
     * @return the hash
     */
    public String generateAutoLoginUserHash(String securityHash) {
        return MD5.hash(securityHash);
    }


}
