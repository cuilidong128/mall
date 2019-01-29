package com.mall.sales.modle;

import io.swagger.models.auth.In;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by cuilidong on 2019/1/13.
 */
public class ActivityGoods {

    private Integer activityGoodsId;

    private Integer activityId;// 活动Id

    private Integer goodsId;// 活动商品

    private Integer agStatus;

    private Integer agUserId;// 活动审核管理员

    /**
     * 活动商品类型
     *
     * 0 商家活动商品，1  平台自营活动商品
     */
    private Integer agType;

    /**
     * 手机客户端推荐
     * 1 推荐，推荐后在手机客户端首页显示
     */
    private Integer mobileRecommend;

    /**
     * 推荐时间
     */
    private Date mobileRecommendTime;


}
