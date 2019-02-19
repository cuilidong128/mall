package com.mall.business.modle.distribution;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.business.util.ShopHelper;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 分销订单结算实体
 * @author cuilidong
 * @date 2019/2/11 14:39
 */
@Data
@ToString
@Table(name = "distribution_bill")
public class DistributionBill  implements Serializable {

    /**
     * 主键、自增
     */
    @Id
    @GeneratedValue
    @Column(name = "distribution_bill_id")
    private int distributionBillId;

    /**
     * 结算单号
     */
    @Column(name = "distribution_bill_sn")
    private int distributionBillSn;

    /**
     * (结算周期)开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Column(name = "distribution_start_time")
    private Timestamp distributionStartTime;

    /**
     * (结算周期)截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Column(name = "distribution_end_time")
    private Timestamp distributionEndTime;

    /**
     * 分销订单佣金金额
     */
    @Column(name = "distribution_orders_amount")
    private BigDecimal distributionOrdersAmount = new BigDecimal(0);

    /**
     * 出账时间(生成结算单的时间)
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @Column(name = "distribution_create_time")
    private Timestamp distributionCreateTime = ShopHelper.getCurrentTimestamp();

    /**
     * 用户ID
     */
    @Column(name = "member_id")
    private int memberId;

    /**
     * 推广商编号
     */
    @Column(name = "distributor_id")
    private int distributorId;

    /**
     * 开店礼包商品推广订单佣金金额
     */
    @Column(name = "open_shop_amount")
    private BigDecimal openShopAmount = BigDecimal.ZERO;

}
