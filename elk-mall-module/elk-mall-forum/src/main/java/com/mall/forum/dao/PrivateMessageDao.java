package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.Post;
import com.mall.forum.modle.PrivateMessage;

import java.util.List;

/**
 *
 */
public interface PrivateMessageDao  extends BaseDao<PrivateMessage> {

    /**
     * 待开发
     * @param entity
     * @return
     */
    public int addNew(PrivateMessage entity);

    public List<PrivateMessage> getFromInbox(Integer userId);

    public List<PrivateMessage> getFromSentBox(Integer userId);

    public int update(PrivateMessage entity);

}
