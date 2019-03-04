package com.mall.system.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String userName;
    private String passWord;

}