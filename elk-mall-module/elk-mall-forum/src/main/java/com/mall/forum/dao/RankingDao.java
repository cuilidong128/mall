package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.PrivateMessage;
import com.mall.forum.modle.Ranking;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface RankingDao extends BaseDao<Ranking> {

    public List<Ranking> selectAll();
}
