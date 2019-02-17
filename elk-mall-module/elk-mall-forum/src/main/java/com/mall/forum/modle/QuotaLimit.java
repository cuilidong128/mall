package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "jforum_quota_limit")
public class QuotaLimit {
    @Id
    @Column(name = "quota_limit_id")
    private int id;

    @Column(name = "quota_desc")
    private String description;

    @Column(name = "quota_limit")
    private int size;

    @Column(name = "quota_type")
    private int type;


}
