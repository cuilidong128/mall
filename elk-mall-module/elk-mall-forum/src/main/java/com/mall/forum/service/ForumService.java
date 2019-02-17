package com.mall.forum.service;

import com.mall.forum.modle.Forum;
import com.mall.forum.modle.Group;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/3.
 */
public interface ForumService {

    public List<Forum> selectAll();

    public void add(Forum forum);

    public void update(Forum forum);

    public void upForumOrder(int forumId);

    public void downForumOrder(int forumId);
}
