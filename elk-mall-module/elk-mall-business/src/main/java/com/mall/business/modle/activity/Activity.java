package com.mall.business.modle.activity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 平台活动数据表
 * @author cuilidong
 * @date 2019/2/11 13:40
 */
@Data
@ToString
@Table(name = "activity")
public class Activity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "activity_id")
    private int activityId;

    /**
     * 活动状态
     * 0.关闭 1.开启
     */
    @Column(name = "activity_state")
    private int activityState ;
    /**
     * 活动类型
     */
    @Column(name = "activity_type")
    private int activityType ;

    /**
     * 活动数据
     */
    @Column(name = "activity_json")
    @Lob
    @NotNull
    private String activityJson;

    /**
     * 修改日期
     */
    @Column(name="update_time")
    private Timestamp updateTime ;
}
