package com.mall.forum.modle;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 论坛附件信息
 */

@Data
@Entity
@ToString
@Table(name = "jforum_attach")
public class Attachment {

    @Id
    @Column(name = "attach_id")
    private int id;

    /**
     * 多对一
     */
    private Post post;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "download_count")
    private int downloadCount;

    @Column(name = "physical_filename")
    private String physicalFilename;

    @Column(name = "real_filename")
    private String realFilename;

    @Column(name = "description")
    private String description;

    @Column(name = "mimetype")
    private String mimetype;

    @Column(name = "upload_date")
    private Date uploadDate;

    @Column(name = "filesize")
    private long filesize;

    @Column(name = "thumb")
    private boolean hasThumb;

    @Column(name = "file_extension")
    private String fileExtension;


    public void incrementDownloadCount() {
        this.downloadCount++;
    }
}
