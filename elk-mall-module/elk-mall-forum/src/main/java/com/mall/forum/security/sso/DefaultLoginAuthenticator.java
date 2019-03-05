package com.mall.forum.security.sso;

import com.mall.forum.dao.UserDao;
import com.mall.forum.modle.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultLoginAuthenticator implements LoginAuthenticator {
    private UserDao repository;


    /**
     * @see com.mall.forum.security.sso.LoginAuthenticator#validateLogin(String, String, java.util.Map)
     */
    @Override
    public User validateLogin(String username, String password, Map<String, Object> extraParams) {
        User user = this.repository.validateLogin(username, password);

        if (user != null && !user.isDeleted() && (user.getActivationKey() == null || user.isActive())) {
            return user;
        }

        return null;
    }
}