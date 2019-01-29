package com.mall.customer.modle;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * 收货地址
 * Created by cuilidong on 2019/1/13.
 */
public class Address {

    @Id
    @Column(name = "address_id")
    private Integer addressId;

    /**
     * 地址对应的用户
     */
    @Column(name = "customer_id")
    private Integer customerId;//
    /**
     * 收货人姓名
     */
    private String trueName;

    /**
     * 收货人详细地址
     */
    private String areaInfo;

    /**
     * 邮政编码
     */
    private String zip;

    private String telephone;// 联系电话

    private String mobile;// 手机号码

    /**
     * 是否为默认收货地址，1为默认地址
     */

    private Integer defaultVal;

    private Date createDate;

    private Date updateDate;
}
