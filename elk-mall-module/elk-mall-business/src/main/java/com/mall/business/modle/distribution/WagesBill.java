package com.mall.business.modle.distribution;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author cuilidong
 * @date 2019/2/11 14:42
 */
@Data
@ToString
@Table(name = "wages_bill")
public class WagesBill implements Serializable {
}
