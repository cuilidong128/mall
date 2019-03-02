package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Ranking;
import com.mall.forum.modle.Topic;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface RecentTopicsDao extends BaseDao<Topic> {

    /**
     * 待开发
     * @param count
     * @return
     */
    public List<Topic> getNewTopics(int count);

    public List<Topic> getUpdatedTopics(int count);

    public List<Topic> getHotTopics(int count);
}
