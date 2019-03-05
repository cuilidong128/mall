package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.ModerationLog;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface ModerationLogDao  extends BaseDao<ModerationLog> {

    public List<ModerationLogDao> selectAll();
}
