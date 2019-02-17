package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 主题风格
 */

@Data
@Entity
@ToString
@Table(name = "jforum_themes")
public class Theme {

    @Id
    @Column(name = "theme_id")
    private int id;

    @Column(name = "style_name")
    private String styleName;

    @Column(name = "template_name")
    private String templateName;

}
