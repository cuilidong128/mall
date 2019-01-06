package com.mall.customer.modle;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 经验值字典
 * Created by cuilidong on 2019/1/6.
 */
@Data
@ToString
public class ExperDict implements Serializable {

    private static final long serialVersionUID = -2744695388848203079L;

    private Integer dictId;

    private String dictName;

    private Integer dictValue;

    private String desc;

    private Integer status;

    private Integer sortNo;

}
