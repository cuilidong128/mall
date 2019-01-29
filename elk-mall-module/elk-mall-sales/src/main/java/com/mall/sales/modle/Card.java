package com.mall.sales.modle;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cuilidong on 2019/1/11.
 */
@Data
@ToString
public class Card {

    /**
     * 卡ID
     */
    private Integer cardId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 卡密码
     */
    private String cardPasswd;

    /**
     * 卡类型
     */
    private Integer cardTypeId;


    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 卡状态
     */
    private Integer status;

    /**
     * 申请卡种 1 借记卡 2 信用卡
     */
    private Integer applyCardType;

    /**
     * 有效期
     */
    private Integer validityTime;


    private BigDecimal cardAmount;// 购买金额


}
