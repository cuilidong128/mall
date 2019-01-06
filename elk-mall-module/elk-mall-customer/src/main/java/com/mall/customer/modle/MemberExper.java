package com.mall.customer.modle;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员经验
 * Created by cuilidong on 2019/1/6.
 */
@Data
@ToString
public class MemberExper implements Serializable {

    private static final long serialVersionUID = -2744695388848203079L;

    private Integer experId;

    /**
     * 会员ID
     */
    private Integer customerId;
    /**
     * 会员总经验值
     */
    private Integer experTotal;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 最后更新的经验值明细ID
     */
    private Integer lastExperDetailedId;

}
