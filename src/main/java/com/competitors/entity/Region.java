package com.competitors.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Region implements Serializable {

    private static final long serialVersionUID = 4555166320070389670L;

    private int id;
    /** 区域代码 */
    private String code;
    /** 父区域代码 */
    @JsonIgnore
    private String parentCode;
    @JsonIgnore
    private Region parent;

    /** 区域名 */
    private String name;

    private int level;
    @JsonIgnore
    private boolean isLast;
    @JsonIgnore
    private String phoneCode;
}
