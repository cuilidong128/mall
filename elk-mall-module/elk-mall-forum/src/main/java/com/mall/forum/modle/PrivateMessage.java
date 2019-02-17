package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "jforum_privmsgs")
public class PrivateMessage implements Serializable {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "jforum_privmsgs_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @Column(name = "privmsgs_id")
    private int id;

    @Column(name = "privmsgs_type")
    private int type;

    @ManyToOne
    @JoinColumn(name = "privmsgs_from_userid")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "privmsgs_to_userid")
    private User toUser;

    @Column(name = "privmsgs_date")
    private Date date;

    @Column(name = "privmsgs_text")
    private String text;

    @Column(name = "privmsgs_subject")
    private String subject;

    @Column(name = "privmsgs_enable_bbcode")
    private boolean bbCodeEnabled = true;

    @Column(name = "privmsgs_enable_html")
    private boolean htmlEnabled = true;

    @Column(name = "privmsgs_enable_smilies")
    private boolean smiliesEnabled = true;

    @Column(name = "privmsgs_attach_sig")
    private boolean signatureEnabled = true;

    @Column(name = "privmsgs_ip")
    private String ip;

    public PrivateMessage() { }

    /**
     * Copy constructor
     *
     * @param pm the object to copy from
     */
    public PrivateMessage(PrivateMessage pm) {
        this.setId(pm.getId());
        this.setType(pm.getType());
        this.setText(pm.getText());
        this.setSubject(pm.getSubject());
        this.setFromUser(pm.getFromUser());
        this.setToUser(pm.getToUser());
        this.setDate(pm.getDate());
    }

    public Post asPost() {
        Post post = new Post();

        post.setSubject(this.subject);
        post.setText(this.text);
        post.setBbCodeEnabled(this.isBbCodeEnabled());
        post.setHtmlEnabled(this.isHtmlEnabled());
        post.setSmiliesEnabled(this.isSmiliesEnabled());
        post.setSignatureEnabled(this.isSignatureEnabled());

        return post;
    }
}
