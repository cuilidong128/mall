package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "jforum_sessions")
public class Session {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "session_start")
    private Date start;

    @Column(name = "session_last_accessed")
    private Date lastAccessed;

    @Column(name = "session_last_visit")
    private Date lastVisit;

    @Column(name = "session_ip")
    private String ip;
}
