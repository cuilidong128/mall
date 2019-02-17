package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 表情符号
 */
@Data
@Entity
@ToString
@Table(name = "jforum_smilies")
public class Smilie implements Serializable {
    @Id
    @Column(name = "smilie_id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "disk_name")
    private String diskName;

}
