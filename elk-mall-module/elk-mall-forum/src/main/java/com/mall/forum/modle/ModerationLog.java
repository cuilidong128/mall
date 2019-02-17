package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "jforum_moderation_log")
public class ModerationLog {

    @Id
    @Column(name = "log_id")
    private int id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "topic_id")
    private int topicId;

    @Column(name = "user_id")
    private User user;

    @Column(name = "post_user_id")
    private User posterUser = new User();

    @Column(name = "log_description")
    private String description = "";

    @Column(name = "log_original_message")
    private String originalMessage;

    @Column(name = "log_date")
    private Date date;

    @Column(name = "log_type")
    private int type;
}
