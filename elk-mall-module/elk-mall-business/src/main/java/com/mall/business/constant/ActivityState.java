package com.mall.business.constant;

/**
 * @author cuilidong
 * @date 2019/2/11 13:51
 */
public class ActivityState {
    /**
     * 默认的砸蛋背景图
     */
    public static final String DEFAULT_GOLD_EGG_BACKGROUND = "img/default_activity_egg_bg.png" ;

    /**
     * 活动类型砸金蛋
     */
    public static final int ACTIVITY_TYPE_GOLD_EGG = 0;
    /**
     * 活动类型砸金蛋文字
     */
    public static final String ACTIVITY_TYPE_GOLD_EGG_TEXT = "幸运砸蛋";

    /**
     * 活动类型 会员签到
     */
    public static final int ACTIVITY_TYPE_MEMBER_SIGNIN = 1;

    /**
     * 奖项类型 - 实物奖品
     */
    public static final String ACTIVITY_PRIZE_TYPE_GIFT = "gift";
    /**
     * 奖项类型 - 实物奖品文字
     */
    public static final String ACTIVITY_PRIZE_TYPE_GIFT_TEXT = "实物奖品";
    /**
     * 奖项类型 - 品币
     */
    public static final String ACTIVITY_PRIZE_TYPE_COUPON = "coupon";
    /**
     * 奖项类型 - 品币文字
     */
    public static final String ACTIVITY_PRIZE_TYPE_COUPON_TEXT = "品币";

    /**
     * 中奖状态 中奖
     */
    public static final int ACTIVITY_STATE_AWARD = 1 ;
    /**
     * 中奖状态 未中奖
     */
    public static final int ACTIVITY_STATE_UNAWARD = 0 ;

    /**
     *  参加限制 0.次/活动全程 1.次/每天
     */
    public static final int ACTIVITY_JOIN_LIMIT_DAY = 1 ;

    public static final int ACTIVITY_JOIN_LIMIT_ALL = 0 ;

    /**
     * 最多中奖限制 0.次/活动全程 1.次/每天
     */
    public static final int ACTIVITY_WIN_LIMIT_DAY = 1 ;

    public static final int ACTIVITY_WIN_LIMIT_ALL = 0 ;

    /**
     * 管理员修改砸金蛋活动的日志信息
     */
    public static final String ADMIN_MOD_GOLD_EGG_LOG = "";
}
