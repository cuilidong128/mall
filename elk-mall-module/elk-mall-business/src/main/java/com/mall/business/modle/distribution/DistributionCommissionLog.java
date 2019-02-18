package com.mall.business.modle.distribution;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.business.constant.DistributionCommissionState;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author cuilidong
 * @date 2019/2/11 14:39
 */
@Data
@ToString
@Table(name = "distributor_commission_log")
public class DistributionCommissionLog implements Serializable {
    /**
     * 自增编码
     */
    @Id
    @GeneratedValue
    @Column(name="log_id")
    private int logId;

    /**
     * 推广商ID
     */
    @NotNull
    @Column(name="distributor_id")
    private int distributorId = 0;

    /**
     * 会员编号
     */
    @NotNull
    @Column(name="member_id")
    private int memberId = 0;
    /**
     * 会员名称
     */
    @Column(name="member_name")
    private String memberName = "";
    /**
     * 管理员编号
     */
    @Column(name="admin_id")
    private int adminId = 0;
    /**
     * 管理员名称
     */
    @Column(name="admin_name")
    private String adminName = "";
    /**
     * 操作阶段（PredepositLogOperationStage状态）
     */
    @NotNull
    @Column(name="operation_stage", length = 50)
    private String operationStage = "";
    /**
     * 可用金额变更
     */
    @Column(name="available_amount")
    private BigDecimal availableAmount = BigDecimal.ZERO;
    /**
     * 冻结金额变更
     */
    @Column(name="freeze_amount")
    private BigDecimal freezeAmount = BigDecimal.ZERO;
    /**
     * 添加时间
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    @Column(name="add_time")
    private Timestamp addTime;
    /**
     * 描述
     */
    @Column(name="description")
    private String description = "";
    /**
     * 状态文本
     */
    @Transient
    private String operationStageText = "";

    public String getOperationStageText() {
        if(operationStage == DistributionCommissionState.INCREASE) {
            operationStageText = "分佣成功增加佣金";
        } else if(operationStage == DistributionCommissionState.CASHAPPLY) {
            operationStageText = "申请提现";
        } else if(operationStage == DistributionCommissionState.CASHPAY) {
            operationStageText = "提现成功";
        } else if(operationStage == DistributionCommissionState.CASHDEL) {
            operationStageText = "取消提现申请";
        } else if(operationStage == DistributionCommissionState.CASHREFUSE) {
            operationStageText = "拒绝提现申请";
        } else if(operationStage == DistributionCommissionState.REFUND) {
            operationStageText = "退款";
        }
        return operationStageText;
    }
}
