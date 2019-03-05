package com.mall.forum.security.sso;

import com.mall.forum.modle.UserSession;
import com.mall.forum.util.ConfigKeys;
import com.mall.forum.util.JForumConfig;

import javax.servlet.http.HttpServletRequest;

public class RemoteUserSSO implements SSO {


    private JForumConfig config;


    @Override
    public String authenticateUser(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    @Override
    public boolean isSessionValid(UserSession userSession) {
        String remoteUser = userSession.getRequest().getRemoteUser();

        // user has since logged out
        if (remoteUser == null && userSession.getUser().getId() != this.config.getInt(ConfigKeys.ANONYMOUS_USER_ID)) {
            return false;
        }
        // user has since logged in
        else if (remoteUser != null && userSession.getUser().getId() == this.config.getInt(ConfigKeys.ANONYMOUS_USER_ID)) {
            return false;
        }
        // user has changed user
        else if (remoteUser != null && !remoteUser.equals(userSession.getUser().getUsername())) {
            return false;
        }

        return true;
    }


    @Override
    public void setConfig(JForumConfig config) {
        this.config = config;
    }
}
