package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
@Table(name = "jforum_attach")
public class AttachmentQuota {

    @Transient
    public static final int KB = 1;

    @Transient
    public static final int MB = 2;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "quota_desc")
    private String description;

    @Column(name = "quota_limit")
    private int size;

    @Column(name = "quota_type")
    private int type;


    public boolean exceedsQuota(long size) {
        if (this.type == AttachmentQuota.KB) {
            return (size > this.size * 1024);
        }

        return (size > this.size * 1024 * 1024);
    }

    public int getSizeInBytes() {
        if (this.type == AttachmentQuota.KB) {
            return (this.size * 1024);
        }

        return (this.size * 1024 * 1024);
    }
}
