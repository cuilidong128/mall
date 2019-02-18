package com.mall.business.modle;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域
 * Created by cuilidong on 2019/1/13.
 */
@Data
@ToString
public class Area {


    private Integer areaId;

    private String areaName;// 区域名称

    /**
     * 下级区域
     */

    private List<Area> childsArea;

    /**
     * 上级区域
     */
    private Integer parentId;

    /**
     * 序号
     */
    private Integer sequence;

    /**
     * 层级
     */
    private Integer level;

    /**
     *  常用地区，设置常用地区后该地区出现在在店铺搜索页常用地区位置
     */
    private boolean common;





}
