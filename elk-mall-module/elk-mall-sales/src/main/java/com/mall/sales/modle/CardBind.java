package com.mall.sales.modle;

import lombok.Data;
import lombok.ToString;

/**
 * Created by cuilidong on 2019/1/12.
 */

@Data
@ToString
public class CardBind {
    /**
     * 卡绑定ID
     */
    private Integer cardBindId;

    /**
     * 卡ID
     */
    private Integer cardId;
    /**
     * 会员ID
     */
    private Integer customerId;
    /**
     * 使用情况状态，默认为0，,使用后为1,-1为过期
     */
    private Integer isUse;

    private String remark; //备注

}
