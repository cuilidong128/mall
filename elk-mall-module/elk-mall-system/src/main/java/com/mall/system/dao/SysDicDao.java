package com.mall.system.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.system.model.SysDic;
import com.mall.system.model.SysDicTerm;

import java.util.List;



/**
 * 数据字典明细表
 *
 * @author king chen
 * @email 396885563@qq.com
 * @date 2018-05-08 17:26:32
 */
public interface SysDicDao extends BaseDao<SysDic> {

    /**
     * 根据字典编码查询字典项
     * @param code
     * @return
     */
    List<SysDicTerm> queryDicTerm(Object code);

    /**
     * 根据父节点查询
     * @param parentd
     * @return
     */
    List<SysDic> queryParentList(Object parentId);

}
