package com.mall.common.base.dao;

import java.util.List;
import java.util.Map;


public interface BaseDao<T> {

    int save(T t);

    int save(Map<String, Object> map);

    int saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int delete(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] id);

    T queryObject(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryBatch(Object[] id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
