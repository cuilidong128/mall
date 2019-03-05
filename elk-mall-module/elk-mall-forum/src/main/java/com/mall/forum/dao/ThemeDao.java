package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Smilie;
import com.mall.forum.modle.Theme;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface ThemeDao extends BaseDao<Theme> {

    public List<Theme> getAll();
}
