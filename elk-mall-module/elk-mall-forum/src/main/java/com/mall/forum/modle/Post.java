package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "jforum_posts")
public class Post implements Serializable {

    @Id
    @Column(name = "post_id")
    private int id;

    @Column(name = "post_date")
    private Date date;

    @Column(name = "post_text")
    private String text;

    @Column(name = "post_subject")
    private String subject;

    @Column(name = "enable_bbcode")
    private boolean bbCodeEnabled = true;

    @Column(name = "enable_html")
    private boolean htmlEnabled = false;

    @Column(name = "enable_smilies")
    private boolean smiliesEnabled = true;

    @Column(name = "enable_sig")
    private boolean signatureEnabled = true;

    @Column(name = "poster_ip")
    private String userIp;

    @Column(name = "attach")
    private boolean hasAttachments;

    @Column(name = "need_moderate")
    private boolean moderate;

    @Column(name = "post_edit_count")
    private int editCount;

    @Column(name = "post_edit_time")
    private Date editDate;


    //@ManyToOne
    private Topic topic;


    //@ManyToOne
    private Forum forum;


    //@ManyToOne
    private User user;


    /**
     * 一对多
     */
    private List<Attachment> attachments = new ArrayList<Attachment>();

    @Transient
    private boolean notifyReplies;

    @Transient
    private Boolean hasEditTimeExpired = Boolean.FALSE;
}
