package com.mall.system.service.impl;

import com.mall.common.base.service.BaseServiceImpl;
import com.mall.system.dao.SysDicDao;
import com.mall.system.model.SysDic;
import com.mall.system.model.SysDicTerm;
import com.mall.system.service.SysDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysDicService")
public class SysDicServiceImpl extends BaseServiceImpl<SysDic> implements SysDicService {

    @Autowired
    private SysDicDao sysDicDao;

    @Transactional(readOnly = true)
    public List<SysDicTerm> queryDicTerm(Object code) {
        return sysDicDao.queryDicTerm(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysDic> queryParentList(Object parentId) {
        return sysDicDao.queryParentList(parentId);
    }

    @Override
    @Transactional
    public void save(SysDic config) {
        sysDicDao.save(config);

    }


}