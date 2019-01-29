package com.mall.sales.modle;

import lombok.ToString;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 卡批次
 */
@lombok.Data
@ToString
public class CardBatch {

    /**
     * 卡批次ID
     */
    private Integer batchId;

    /**
     * 批次名称
     */
    private String batchName;

    /**
     * 卡封面图片
     */
    private String cardCoverPic;

    /**
     * 批次描述
     */
    private String batchDesc;

    /**
     * 有效期开始
     */
    private Date validStartDate;

    /**
     * 有效期结束
     */
    private Date validEndDate;

    /**
     * 批次状态 1 发行中 0 停止发行
     */
    private Integer status;

    /**
     * 发行的卡数量
     */
    private Integer cardCount;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 卡种类
     */
    private Integer cardTypeId;


    /**
     * 申请卡种 1 借记卡 2 信用卡
     */
    private Integer applyCardType;




}
