package com.mall.forum.web;

import com.mall.common.util.ResponseMessage;
import com.mall.forum.core.helpers.Domain;
import com.mall.forum.modle.User;
import com.mall.forum.modle.UserSession;
import com.mall.forum.service.GroupService;
import com.mall.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


import static com.mall.common.util.ResponseMessage.ok;
import static com.mall.common.util.ResponseMessage.error;
@RestController
@RequestMapping(Domain.USER)
public class UserAction {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSession userSession;

    @PostMapping("/login")
    public ResponseMessage<Map<String, Object>> login(String username, String password,boolean autoLogin) throws Exception {

        User user =userService.validateLogin(username,password);
        if(user==null){
            return error("用户名或密码错误");
        }

        this.userSession.setUser(user);
        this.userSession.becomeLogged();

        if (autoLogin) {
            this.activateAutoLogin(user);
        }
        else {
            this.removeAutoLoginCookies(this.userSession);
        }

        return ok();
    }

    private void activateAutoLogin(User user) {
        String securityHash = this.userService.generateAutoLoginSecurityHash(user.getId());
        user.setSecurityHash(securityHash);

        String userHash = this.userService.generateAutoLoginUserHash(securityHash);

//        this.userSession.addCookie(this.config.getValue(ConfigKeys.COOKIE_AUTO_LOGIN), "1");
//        this.userSession.addCookie(this.config.getValue(ConfigKeys.COOKIE_USER_HASH), userHash);
//        this.userSession.addCookie(this.config.getValue(ConfigKeys.COOKIE_USER_ID), Integer.toString(user.getId()));
    }

    private void removeAutoLoginCookies(UserSession us) {
//        us.removeCookie(this.config.getValue(ConfigKeys.COOKIE_AUTO_LOGIN));
//        us.removeCookie(this.config.getValue(ConfigKeys.COOKIE_USER_HASH));
    }
}
