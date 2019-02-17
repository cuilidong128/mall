package com.mall.common.mongodb.repo;

import org.springframework.stereotype.Repository;

import com.mall.common.mongodb.model.ExceptionLogVO;
import com.mall.common.mongodb.mongo.BaseMongoRepositoryImpl;

/**
 * 异常日志数据接口实现类
 */

//@Repository
public class ExceptionLogRepoImpl extends BaseMongoRepositoryImpl<ExceptionLogVO, String> implements ExceptionLogRepo {


}
