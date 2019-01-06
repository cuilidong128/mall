package com.mall.customer.modle;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.mall.common.annotation.PropertyExt;
import com.mall.common.util.validator.group.AddGroup;
import com.mall.common.util.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 客户信息表
 */
@Data
@ToString
public class Customer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2744695388848203079L;

    /**
     * 客户ID
     */
    @Id
    @ApiModelProperty("客户id")
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 用户名
     */
    @ApiModelProperty("客户姓名")
    @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max=20)
    @PropertyExt
    private String customerName;
    private String moble;
    private String customerPosition;
    private String registerIpAddress;
    private String registerPort;
    private Date  registerTime;
    private String note;

    /**
     * 会员等级
     */
    private Integer gradeId;


    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 手势密码
     */
    private String gesturePassword;


}
