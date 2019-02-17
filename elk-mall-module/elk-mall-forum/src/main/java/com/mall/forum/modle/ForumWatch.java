package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@ToString
@Table(name = "jforum_forums_watch")
public class ForumWatch {
    @Id
    private int id;
    @Column(name = "forum_id")
    private int forumId;
    @Column(name = "user_id")
    private int userId;

    public ForumWatch() {}

    public ForumWatch(int forumId, int userId) {
        this.setUserId(userId);
        this.setForumId(forumId);
    }
}
