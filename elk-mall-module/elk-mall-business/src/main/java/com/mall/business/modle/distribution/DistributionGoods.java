package com.mall.business.modle.distribution;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author cuilidong
 * @date 2019/2/11 14:40
 */
@Data
@ToString
@Table(name = "distribution_goods")
public class DistributionGoods  implements Serializable {

    /**
     * 商品SPU编号
     */
    @Id
    @Column(name = "common_id")
    private int commonId;
    /**
     * 店铺编号
     */
    @Column(name = "store_id")
    private int storeId;
    /**
     * 佣金比例
     */
    @Min(0)
    @Max(50)
    @Column(name = "commission_rate")
    private int commissionRate = 0;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @Column(name = "add_time")
    private Timestamp addTime;
    /**
     * 总推广量（订单数量）
     */
    @Column(name = "orders_count")
    private long ordersCount = 0;
    /**
     * 总佣金
     */
    @Column(name = "commission_total")
    private BigDecimal commissionTotal = BigDecimal.ZERO;
}
