package com.mall.forum.modle;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户头像
 */

@Data
@Entity
@ToString
@Table(name = "jforum_avatar")
public class Avatar implements Serializable{

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "avatar_type", updatable = false)
    @Enumerated(EnumType.STRING)
    private AvatarType avatarType = AvatarType.AVATAR_GALLERY;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;


}
