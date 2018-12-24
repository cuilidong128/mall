package com.mall.system.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.system.model.SysConfig;
import org.apache.ibatis.annotations.Param;



/**
 * 系统配置信息
 * @author King chen
 * @email 396885563@qq.com
 * @date 2017年12月29日
 */
public interface SysConfigDao extends BaseDao<SysConfig> {

    /**
     * 根据key，查询value
     */
    SysConfig queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);

}
