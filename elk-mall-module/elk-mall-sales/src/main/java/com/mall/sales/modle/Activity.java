package com.mall.sales.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 活动
 * Created by cuilidong on 2019/1/13.
 */
@Data
@ToString
public class Activity {

    /**
     * ID
     */
    private Integer activityId;

    private String activityTitle;// 活动标题
    private Date activityBeginDate;// 开始时间

    private Date activityEndTime;// 结束时间
    private Integer activityAccId;// 标题页面横幅,主图片最佳尺寸：737X320
    private Integer activityAccId2;// 横幅旁小图片，最佳尺寸200X320
    private Integer activityAccId3;// 活动专题页右侧图片,最佳尺寸250X320
    private Integer acSequence;// 活动序号
    private Integer acStatus;// 活动状态，0为关闭，1为启动
    private String acContent;// 活动说明

    private List<ActivityGoods> activityGoods;


}
