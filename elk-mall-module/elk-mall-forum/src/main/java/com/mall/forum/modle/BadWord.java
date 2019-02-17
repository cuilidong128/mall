package com.mall.forum.modle;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 屏蔽词
 */

@Data
@Entity
@ToString
@Table(name = "jforum_attach")
public class BadWord {

    @Id
    @Column(name = "word_id")
    private int id;

    @Column(name = "word")
    private String word;

    @Column(name = "replacement")
    private String replacement;
}
