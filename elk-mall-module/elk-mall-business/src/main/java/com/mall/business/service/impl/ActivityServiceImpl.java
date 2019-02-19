package com.mall.business.service.impl;

import com.mall.business.dao.activity.ActivityDao;
import com.mall.business.service.ActivityService;
import com.mall.common.util.RedisUtil;
import com.mall.common.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuilidong
 * @date 2019/2/12 8:12
 * redis 使用场景: 计数器 排行榜 用于存储时间戳 记录用户判定信息 抽奖列表 缓存 队列 会话缓存
 * String(字符串): 应用数, 资讯数等, (避免了select count(*) from ...)
   Hash（哈希表）: 用户粉丝列表, 用户点赞列表, 用户收藏列表, 用户关注列表等。
   List（列表）：消息队列, push/sub提醒。
   SortedSet（有序集合）：热门列表, 最新动态列表, TopN, 自动排序。
 *
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    RedisUtils redisUtils;



    /**
     * 用户抽奖活动是否参与抽奖(用户是否每天参与抽奖)
     * @param memberId
     * @return
     */
    public  Long activityPartNumber(Integer memberId){
        String key = "activity:memberId:";
        key = key + memberId;
       return redisUtils.inc(key ,new Long(1) );
    }
}
