package com.mall.sales.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cuilidong on 2019/1/12.
 */
@Data
@ToString
public class Coupon {

    private Integer couponId; //优惠券ID

    private String couponName;// 优惠券名称

    private BigDecimal couponAmount;// 优惠券金额

    private Date couponBeginTime;// 优惠券使用开始时间

    private Date couponEndTime;// 优惠券使用结束时间

    private int couponCount;// 优惠券发行数量
    /**
     * 使用的订单金额
     */
    private BigDecimal couponOrderAmount;// 订单满足该金额时才可以使用该优惠券

    private String couponPic;// 优惠券图片

    /**
     * 优惠券类型
     * --平台优惠券，抵消自营商品订单金额，
     * --商家优惠券，抵消订单中该商家商品部分金额
     */
    private Integer couponTypeId;

    /**
     * 对应的商家店铺
     */
    private Integer storeId; // 当优惠券类型为商家优惠券时对应的商家店铺

    /**
     * 是否审核 1 已审核 0待审核
     */
    private Integer audit;
}
