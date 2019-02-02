package com.mall.forum.web;

import com.mall.common.base.model.Response;
import com.mall.common.util.JsonResponse;
import com.mall.common.util.Page;
import com.mall.forum.modle.Group;
import com.mall.forum.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by cuilidong on 2019/2/2.
 */
@RestController
@RequestMapping("/forum/group")
public class GroupAction {

    @Autowired
    private GroupService groupService;

    /**
     * 所有配置列表
     */
    @GetMapping("/list")

    public void list(){

        List<Group> list = groupService.selectAll();

    }
}
