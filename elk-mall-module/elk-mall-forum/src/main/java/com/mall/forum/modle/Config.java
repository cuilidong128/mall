package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * 论坛配置
 */

@Data
@Entity
@ToString
@Table(name = "jforum_categories")
public class Config {

    @Id
    @Column(name = "config_id")
    private int id;

    @Column(name = "config_name")
    private String name;

    @Column(name = "config_value")
    private String value;
}
