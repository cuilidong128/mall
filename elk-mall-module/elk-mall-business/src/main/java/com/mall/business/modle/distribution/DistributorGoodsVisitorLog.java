package com.mall.business.modle.distribution;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author cuilidong
 * @date 2019/2/11 14:41
 */
@Data
@ToString
@Table(name = "distributor_goods_visitor_log")
public class DistributorGoodsVisitorLog implements Serializable {
}
