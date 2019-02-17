package com.mall.forum.modle;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 论坛等级
 */
@Data
@Entity
@ToString
@Table(name = "jforum_ranks")
public class Ranking implements Serializable {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "jforum_ranks_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "rank_id")
    private int id;

    @Column(name = "rank_title")
    private String title;

    @Column(name = "rank_special")
    private boolean special;

    @Column(name = "rank_image")
    private String image;

    @Column(name = "rank_min")
    private int min;

}
