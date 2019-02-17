package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Category;
import com.mall.forum.modle.Config;
import com.mall.forum.modle.Forum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/8.
 */
public interface ConfigDao extends BaseDao<Config> {

    public void addNew(Config config);

    public void updateConfig(Config config);

    public List<Config> selectAll();

    public Config selectById(@Param("configId") int configId);

    public Config selectByName(@Param("name") String name);

    public void delete(@Param("configId") int configId);


}
