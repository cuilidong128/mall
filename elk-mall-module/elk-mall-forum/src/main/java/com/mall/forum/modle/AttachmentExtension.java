package com.mall.forum.modle;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 论坛附件扩展
 */

@Data
@Entity
@ToString
@Table(name = "jforum_attach_extensions")
public class AttachmentExtension {

    @Id
    @Column(name = "extension_id")
    private int id;

    @Column(name = "allow")
    private boolean allow;

    @Transient
    private boolean unknown;

    @Column(name = "description")
    private String description;

    @Column(name = "extension")
    private String extension;


    /**
     * @return Returns the allow.
     */
    public boolean isAllowed() {
        return this.allow;
    }
}
