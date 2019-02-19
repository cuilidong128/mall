package com.mall.business.modle.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.constant.State;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author cuilidong
 * @date 2019/2/11 13:40
 */
@Data
@ToString
@Table(name = "activity_accept_prize")
public class ActivityAcceptPrize implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "accept_prize_id")
    private int acceptPrizeId;
    /**
     * 活动id
     */
    @Column(name = "activity_id")
    private int activityId;

    /**
     * 活动类型
     * 0: 砸蛋
     */
    @Column(name = "activity_type")
    private int activityType;

    /**
     * 消费的积分数量
     */
    @Column(name = "take_points")
    private int takePoints;
    /**
     * 联系电话
     */
    @Column(name = "accept_prize_phone")
    private String acceptPrizePhone;
    /**
     * 姓名
     */
    @Column(name = "accept_prize_name")
    private String acceptPrizeName;
    /**
     * 地址
     */
    @Column(name = "accept_prize_address")
    private String acceptPrizeAddress;

    /**
     * 会员id
     */
    @Column(name = "member_id")
    private int memberId;

    /**
     * 会员名称
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 会员头像
     */
    @Column(name = "member_avatar")
    private String memberAvatar;

    /**
     * 会员头像地址
     */
    @Column(name = "member_avatar_url")
    private String memberAvatarUrl;

    /**
     * 中奖状态
     * 0. 未中奖 1.已中奖
     */
    @Column(name = "state")
    private int state = State.NO;


    /**
     * 认领状态
     * 0.未领取  1.已领取
     */
    @Column(name = "accept_state")
    private int acceptState = State.NO;

    /**
     * 发放状态
     */
    @Column(name = "give_out_state")
    private int giveOutState = State.NO;

    /**
     * 获奖时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @Column(name = "give_out_time")
    private Timestamp giveOutTime;
    /**
     * 奖品类型
     */
    @Column(name = "awards_type")
    private String awardsType;

    /**
     * 奖项 id
     */
    @Column(name = "awards_id")
    private int awardsId;

    /**
     * 奖项名称
     */
    @Column(name = "awards_name")
    private String awardsName;
    /**
     * 奖项内容
     */
    @Column(name = "awards_content")
    private String awardsContent;

    /**
     * 获奖时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @Column(name = "add_time")
    private Timestamp addTime;

    /**
     *  快递公司名称
     */
    @Column(name = "ship_name")
    private String shipName;

    /**
     * 快递单号
     */
    @Column(name = "ship_sn")
    private String shipSn;


    /**
     * 奖品的json信息
     * @return
     */
    @Lob
    @Column(name = "prize_json")
    private String prizeJson;

    /**
     * 活动类型
     */
    @Transient
    private String activityTypeText = "";

    /**
     * 奖品类型
     */
    @Transient
    private String awardsTypeText = "";

    /**
     * 认领状态文字
     */
    @Transient
    private String acceptStateText = "";

    /**
     * 发放状态文字
     */
    @Transient
    private String giveOutStateText = "";
}
