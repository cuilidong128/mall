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
@Table(name = "jforum_groups")
public class Group implements Serializable {

    @Id
    @Column(name = "group_id")
    private int id;
    @Column(name = "parent_Id")
    private int parentId;


    @Column(name = "group_name")
    private String name;
    @Column(name = "group_description")
    private String description;

}
