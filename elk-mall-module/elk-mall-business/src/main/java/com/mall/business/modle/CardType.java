package com.mall.business.modle;

import lombok.Data;
import lombok.ToString;

/**
 * Created by cuilidong on 2019/1/12.
 */
@Data
@ToString
public class CardType {

    /**
     * ID
     */
    private Integer cardTypeId;

    /**
     * 卡类型名称
     */
    private String cardTypeName;

    /**
     * 卡类型描述
     */
    private String cardTypeDesc;

}
