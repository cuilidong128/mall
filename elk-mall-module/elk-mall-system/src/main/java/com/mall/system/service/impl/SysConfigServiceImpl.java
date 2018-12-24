package com.mall.system.service.impl;

import com.mall.common.base.service.BaseServiceImpl;
import com.mall.common.util.exception.RRException;
import com.mall.system.dao.SysConfigDao;
import com.mall.system.model.SysConfig;
import com.mall.system.service.SysConfigService;
import com.mall.utils.SysConfigRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
@Service("sysConfigService")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {
    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private SysConfigRedis sysConfigRedis;

    @Override
    @Transactional
    public void save(SysConfig config) {
        sysConfigDao.save(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional
    public void update(SysConfig config) {
        sysConfigDao.update(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional
    public void updateValueByKey(String key, String value) {
        sysConfigDao.updateValueByKey(key, value);
        sysConfigRedis.delete(key);
    }

    @Override
    @Transactional
    public void deleteBatch(Object[] ids) {
        for(Object id : ids){
            SysConfig config = queryObject(id);
            sysConfigRedis.delete(config.getKey());
        }

        sysConfigDao.deleteBatch(ids);
    }


    @Override
    public String getValue(String key) {
        SysConfig config = sysConfigRedis.get(key);
        if(config == null){
            config = sysConfigDao.queryByKey(key);
            sysConfigRedis.saveOrUpdate(config);
        }

        return config == null ? null : config.getValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if(StringUtils.isNotBlank(value)){
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RRException("获取参数失败");
        }
    }
}

