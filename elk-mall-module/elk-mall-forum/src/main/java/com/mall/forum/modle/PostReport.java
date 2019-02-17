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
@Table(name = "jforum_post_report")
public class PostReport {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "jforum_post_report_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "report_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "report_date")
    private Date date;

    @Column(name = "report_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status")
    private PostReportStatus status = PostReportStatus.UNRESOLVED;

    public PostReport() {
    }

    public PostReport(int id, int postId, String postSubject, int topicId, Date reportDate, String description,
                      String reporterName, int reporterId, String postUser, int postUserId, PostReportStatus status) {
        this.id = id;
        this.description = description;

        this.date = reportDate;
        this.status = status;
        this.post = new Post();
        this.post.setId(postId);
        this.post.setSubject(postSubject);
//        this.post.setTopic(new Topic());
//        this.post.getTopic().setId(topicId);
//        this.post.setUser(new User());
//        this.post.getUser().setUsername(postUser);
//        this.post.getUser().setId(postUserId);

        this.user = new User();
        this.user.setUsername(reporterName);
        this.user.setId(reporterId);
    }
}
