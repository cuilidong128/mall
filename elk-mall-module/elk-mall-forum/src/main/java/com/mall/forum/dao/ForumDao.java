package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Category;
import com.mall.forum.modle.Forum;
import com.mall.forum.modle.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/3.
 */
public interface ForumDao extends BaseDao<Forum> {

    public List<Forum> selectAll();

    public Forum selectById(@Param("forumId") int forumId);

    public void addNew(Forum forum);

    Integer getTotalPosts(@Param("forumId") Integer forumId);
}
