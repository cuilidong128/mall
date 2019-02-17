package com.mall.forum.service.impl;

import com.mall.forum.dao.GroupDao;
import com.mall.forum.modle.Group;
import com.mall.forum.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/2.
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;

    public List<Group> selectAll(){
       return groupDao.selectAll();
    }
}
