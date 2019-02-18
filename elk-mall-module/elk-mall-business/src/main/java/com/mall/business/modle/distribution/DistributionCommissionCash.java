package com.mall.business.modle.distribution;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.constant.State;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author cuilidong
 * @date 2019/2/11 14:39
 */
@Data
@ToString
@Table(name = "distribution_commission_cash")
public class DistributionCommissionCash  implements Serializable {

    /**
     * 自增编码
     */
    @Id
    @GeneratedValue
    @Column(name = "cash_id")
    private int cashId;
    /**
     * 提现编号
     */
    @NotBlank
    @Column(name = "cash_sn", length = 100, unique = true)
    private String cashSn = "";
    /**
     * 推广商编号
     */
    @NotNull
    @Column(name = "distributor_id")
    private int distributorId = 0;

    /**
     * 会员编号
     */
    @NotNull
    @Column(name = "member_id")
    private int memberId = 0;

    /**
     * 会员名称
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 提现金额
     */
    @NotNull
    @DecimalMin("0.01")
    @Column(name = "amount")
    private BigDecimal amount = new BigDecimal(0);


    /**
     * 绑定的手机号 , 只记申请时的
     */
    @Column(name = "bind_phone")
    private String bindPhone;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号
     */

    @Length(min = 13, max = 18)
    @Column(name = "id_cart_number")
    private String idCartNumber;

    /**
     * 账户类型
     * bank ,alipay
     */
    @Column(name = "account_type")
    private String accountType;
    /**
     * 银行支行名称
     */
    @Column(name = "bank_account_name")
    private String bankAccountName;

    /**
     * 银行收款人姓名
     */
    @Column(name = "pay_person")
    private String payPerson;
    /**
     * 银行账号
     */
    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    /**
     * 添加时间
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Column(name = "add_time")
    private Timestamp addTime;
    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Column(name = "pay_time")
    private Timestamp payTime;
    /**
     * 状态（状态 0未处理 1提现成功  2提现失败）
     */
    @NotNull
    @Column(name = "state")
    private int state = State.NO;
    /**
     * 操作审核的管理员编号
     */
    @Column(name = "admin_id")
    private int adminId;
    /**
     * 操作审核的管理员
     */
    @Column(name = "admin_name")
    private String adminName = "";
    /**
     * 拒绝提现理由
     */
    @Size(max = 1000, message = "拒绝理由长度应小于1000个字")
    @Column(name = "refuse_reason", length = 1000)
    private String refuseReason = "";
    /**
     * 状态文本
     */
    @Transient
    private String stateText = "";
}
