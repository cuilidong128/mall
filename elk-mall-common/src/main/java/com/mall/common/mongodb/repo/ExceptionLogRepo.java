package com.mall.common.mongodb.repo;

import com.mall.common.mongodb.model.ExceptionLogVO;
import com.mall.common.mongodb.mongo.BaseMongoRepository;

/**
 * 异常日志仓库接口
 */
public interface ExceptionLogRepo extends BaseMongoRepository<ExceptionLogVO, String> {

}
