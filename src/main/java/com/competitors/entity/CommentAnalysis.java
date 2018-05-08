package com.competitors.entity;

import com.khazix.core.ColumnIgnore;
import com.khazix.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
@Setter
@Entity
@Table(name = "hotel_score")
public class CommentAnalysis extends BaseEntity{

    private static final long serialVersionUID = 2008721849077875925L;

    @Column(name = "comment_id")
    private int commentId;
    private String phone;
    @Column(name = "score_target")
    private int analysisTarget;
    @ColumnIgnore
    private String analysisTargetName;
    private int score;
    @Column(name = "comment_text")
    private String content;
}
