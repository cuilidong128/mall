package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Group;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/1.
 */
public interface GroupDao extends BaseDao<Group>{

    public List<Group> selectAll();

    public Group selectById(int groupId);

    public boolean canDelete(int groupId);

    public void delete(int groupId);

    public int update(Group group);

    public int addNew(Group group);
}
