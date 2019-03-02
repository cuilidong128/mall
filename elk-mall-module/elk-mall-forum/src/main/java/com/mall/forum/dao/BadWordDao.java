package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.BadWord;

import java.util.List;

/**
 * 敏感词
 */
public interface BadWordDao extends BaseDao<BadWord> {

    /**
     * 获取全部敏感词
     * @return
     */
    public List<BadWord> getAll();


}
