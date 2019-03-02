package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Topic;
import com.mall.forum.modle.TopicWatch;
import com.mall.forum.modle.User;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface TopicWatchDao  extends BaseDao<TopicWatch> {

    public List<User> getUsersWaitingNotification(Topic topic);

    public TopicWatch getSubscription(Topic topic, User user);

    public void removeSubscription(Topic topic, User user);

    public void removeSubscription(Topic topic);

    //private void markAllAsUnread(Topic topic);
}
