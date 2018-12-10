package com.mall.system.web;

import com.mall.common.base.controller.BaseController;
import com.mall.system.model.UpmsUser;
import com.mall.system.service.UpmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;

    //@ApiOperation(value = "用户首页")
    //@RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() {
        UpmsUser user = upmsUserService.selectByPrimaryKey(1);

    }

}
