package com.mall.customer.modle;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 * 会员等级 - 在用户头像处显示
 */
@Data
@ToString
public class MemberGrade implements Serializable {

    private static final long serialVersionUID = -2744695388848203079L;

    private Integer gradeId;
    private String gradeName;
    private String gradeDetail;

    /**
     * 会员等级 新用户-初级 普通会员 中级会员 高级会员  SVIP1 SVIP2 SVIP3 SVIP4 SVIP5
     */
    private Integer level;
    /**
     * 升级分数 新用户-初级 1000 普通会员 5000 中级会员 10000 高级会员 50000 SVIP1 100000
     */
    private Integer upExper;

    private Short status;
    private Short deleted;
    private Integer version;

    /**
     * 等级图标
     */
    private String iconUrl;

    /**
     * 是否为默认等级
     */
    private Integer isDefault;

}
