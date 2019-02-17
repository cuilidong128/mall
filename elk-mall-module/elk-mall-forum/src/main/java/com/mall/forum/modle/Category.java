package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 论坛目录
 */

@Data
@Entity
@ToString
@Table(name = "jforum_categories")
public class Category implements Serializable {

    @Id
    @Column(name = "category_id")
    private int id;

    @Column(name = "category_order")
    private int displayOrder;

    @Column(name = "category_moderated")
    private boolean moderated;

    @Column(name = "category_title")
    private String name;

    @Transient
    private Theme theme;

    private List<Forum> forums = new ArrayList<Forum>();
}
