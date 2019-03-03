package com.mall.forum.security.sso;

import com.mall.forum.modle.User;

import java.util.Map;

public interface LoginAuthenticator {
    /**
     * Authenticates an user.
     *
     * @param username Username
     * @param password Password
     * @param extraParams Extra parameters, if any.
     * @return An instance of a {@link com.mall.forum.modle.User} or <code>null</code>
     */
    public User validateLogin(String username, String password, Map<String, Object> extraParams);
}
