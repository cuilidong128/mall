package com.mall.business.modle.activity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 平台活动赠品
 * @author cuilidong
 * @date 2019/2/11 13:40
 */
@Data
@ToString
@Table(name = "activity_gift")
public class ActivityGift implements Serializable {
    /**
     * 赠品编号<br>
     * 主键、自增
     */
    @Id
    @GeneratedValue
    @Column(name = "gift_id")
    private Integer giftId;

    /**
     * 商品名称
     */
    @NotNull
    @Length(min = 3, max = 50, message = "商品名称为3到50个字符")
    @Column(name = "gift_goods_name")
    private String giftGoodsName;

    /**
     * 赠品显示价格
     */
    @NotNull
    @Column(name = "gift_price")
    private BigDecimal giftPrice = new BigDecimal(0);

    /**
     * 图片名称
     */
    @NotNull
    @Column(name = "gift_image_name")
    private String giftImageName = "";

    /**
     * 赠品状态 0.被删除 1.正常
     */
    @Column(name = "gift_state")
    private int giftState = 1 ;

    /**
     * 图片路径
     */
    @Transient
    private String imageSrc = "";
}
