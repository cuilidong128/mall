package com.mall.sales.modle;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 广告
 * Created by cuilidong on 2019/1/13.
 */
@Data
@ToString
public class Advert {

    private Integer advertId;// 广告ID
    private String advertTitle;// 广告名称

    private Date advertBeginTime;// 开始时间

    private Date advertEndTime;// 结束时间


}
