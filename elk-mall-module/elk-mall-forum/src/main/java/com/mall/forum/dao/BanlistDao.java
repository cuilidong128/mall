package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.BadWord;
import com.mall.forum.modle.Banlist;

import java.util.List;

/**
 * 黑名单
 */
public interface BanlistDao extends BaseDao<Banlist> {

    /**
     * 获取全部黑名单成员
     * @return
     */
    public List<Banlist> getAllBanlists();

}
