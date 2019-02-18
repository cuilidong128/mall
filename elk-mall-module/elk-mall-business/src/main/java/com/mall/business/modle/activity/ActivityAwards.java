package com.mall.business.modle.activity;

import com.mall.common.constant.State;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 活动奖品
 * @author cuilidong
 * @date 2019/2/11 13:40
 */
@Data
@ToString
@Table(name = "activity_awards")
public class ActivityAwards implements Serializable {
    /**
     * 奖项编号<br>
     * 主键、自增
     */
    @Id
    @GeneratedValue
    @Column(name = "awards_id")
    private int awardsId ;

    /**
     * 活动id
     */
    @NotNull
    @Column(name = "activity_id")
    private int activityId ;

    /**
     * 实际奖项id
     */
    @NotNull
    @Column(name = "awards_info_id")
    private int awardsInfoId ;
    /**
     * 奖项的类型
     */
    @NotNull
    @Column(name = "awards_type")
    private String awardsType ;
    /**
     * 奖项名称
     */
    @NotNull
    @Column(name = "awards_name")
    private String awardsName ;
    /**
     * 奖项内容
     */
    @NotNull
    @Column(name = "awards_content")
    private String awardsContent ;
    /**
     * 奖项的中奖概率
     */
    @NotNull
    @Column(name = "awards_rate")
    private Float awardsRate ;
    /**
     * 奖项的剩余数量
     */
    @Column(name = "awards_count")
    private int awardsCount ;

    /**
     * 该奖项发放数量
     */
    @Column(name="awards_give_count")
    private int awardsGiveCount = State.NO;

    /**
     * 奖项状态
     */
    @Column(name="awards_state")
    private int awardsState = State.YES;
}
