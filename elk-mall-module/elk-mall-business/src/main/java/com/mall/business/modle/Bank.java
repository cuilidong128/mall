package com.mall.business.modle;

import lombok.Data;
import lombok.ToString;

/**
 * 银行级别信息
 */
@Data
@ToString
public class Bank {

    /**
     * 银行ID
     */
    private Integer bankId;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行编号
     */
    private String bankCode;
    /**
     * 银行图片
     */
    private String bankImg;

}
