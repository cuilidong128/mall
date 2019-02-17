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
@Table(name = "jforum_topics")
public class Topic implements Serializable {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_STICKY = 1;
    public static final int TYPE_ANNOUNCE = 2;
    public static final int STATUS_UNLOCKED = 0;
    public static final int STATUS_LOCKED = 1;

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "jforum_topics_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "topic_id")
    private int id;

    @Column(name = "forum_id")
    private int forumId;

    @Column(name = "topic_views")
    private int totalViews;

    @Column(name = "topic_replies")
    private int totalReplies;

    @Column(name = "topic_status")
    private int status;

    @Column(name = "topic_type")
    private int type;

    @Column(name = "has_attachment")
    private boolean hasAttachment;

    @Transient
    private boolean paginate;

    @Column(name = "topic_subject")
    private String subject;

    @Column(name = "topic_date")
    private Date date;

    @Column(name = "user_id")
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_first_post_id")
    private Post firstPost;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_last_post_id")
    private Post lastPost;

    @Column(name = "topic_vote_id", updatable = false, insertable = false)
    private Integer pollId;


    @OneToMany(mappedBy = "topic")
    private List<Post> posts = new ArrayList<Post>();

    @Column(name = "need_moderate")
    private boolean pendingModeration;

    @Column(name = "topic_moved_id")
    private int movedId;


    public Topic() {}
}
