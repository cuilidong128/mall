package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Session;
import com.mall.forum.modle.Theme;
import com.mall.forum.modle.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/3/3.
 */
public interface SessionDao extends BaseDao<Session> {

    /**
     * Always execute saveOrUpdate().
     */

    public void add(Session entity);


    public Session get(@Param("userId") int userId);




}
