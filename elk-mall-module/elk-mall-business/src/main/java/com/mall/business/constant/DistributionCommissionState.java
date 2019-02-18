package com.mall.business.constant;

/**
 * @author cuilidong
 * @date 2019/2/11 14:48
 */
public class DistributionCommissionState {
    /**
     * 分佣成功 ，增加可提现佣金
     */
    public static final String INCREASE = "increase";
    /**
     * 退款， 减少佣金
     */
    public static final String REFUND = "refund";

    /**
     * 申请提现，冻结佣金
     */
    public static final String CASHAPPLY = "cashApply";
    /**
     * 提现成功，减少冻结佣金
     */
    public static final String CASHPAY = "cashPay";
    /**
     * 取消提现申请，解冻佣金
     */
    public static final String CASHDEL = "cashDel";
    /**
     * 拒绝提现申请，解冻佣金
     */
    public static final String CASHREFUSE = "cashRefuse";



    /**
     * 申请提现 状态 未处理
     */
    public static final int CAST_NEW = 0;
    /**
     * 申请提现 状态 成功
     */
    public static final int CAST_SUCCESS = 1;
    /**
     * 申请提现 状态 失败
     */
    public static final int CAST_FAILED = 2;
}
