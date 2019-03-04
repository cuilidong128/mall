package com.mall.common.mongodb.repo;

import org.springframework.stereotype.Repository;

import com.mall.common.mongodb.model.SysLogVO;
import com.mall.common.mongodb.mongo.BaseMongoRepositoryImpl;

/**
 * 日志数据接口实现类
 */

@Repository
public class SysLogRepoImpl extends BaseMongoRepositoryImpl<SysLogVO, String> implements SysLogRepo {


}
