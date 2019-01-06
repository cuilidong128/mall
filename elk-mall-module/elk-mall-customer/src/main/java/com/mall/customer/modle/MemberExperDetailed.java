package com.mall.customer.modle;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 经验值明细
 * Created by cuilidong on 2019/1/6.
 */
@Data
@ToString
public class MemberExperDetailed implements Serializable {

    private static final long serialVersionUID = -2744695388848203079L;

    /**
     * 明细ID
     */
    private Integer detailedId;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 经验值类型名称
     */
    private String experDict;

    /**
     * 经验值
     */
    private Integer experV;

    /**
     * 描述
     */
    private String desc;

    /**
     * 创建日期
     */
    private Date createTime;

}
