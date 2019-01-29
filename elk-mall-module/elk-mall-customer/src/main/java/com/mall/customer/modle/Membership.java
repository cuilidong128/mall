package com.mall.customer.modle;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

/**
 * 会籍
 */
public class Membership {

    /**
     * 会籍
     */
    private Integer membershipId;

    private String membershipCode;

    private String membershipName;

    /**
     * 服务费用
     */
    private BigDecimal serviceFee;

    /**
     * 充值金额
     */

    private BigDecimal rechargeFee;

    private Data createTime;//创建时间

    private Integer status;//状态

}
