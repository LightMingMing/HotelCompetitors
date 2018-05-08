package com.competitors.entity;

import com.khazix.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "analysis_day_comment")
@Setter
@Getter
public class DayAnalysis extends BaseEntity {

    private static final long serialVersionUID = 640077941274370516L;

    private String phone;
    private String hotelId;
    @Column(name = "analysis_date")
    private String analysisDate;
    private Integer webId;
    @Column(name = "avg_grade")
    private Float avgGrade;
    @Column(name = "comment_number")
    private Integer commentNumber;
    @Column(name = "response_number")
    private Integer responseNumber;
    @Column(name = "to_response_number")
    private Integer toResponseNumber;
    // 意见数量 = 批评数量 + 表扬数量 + 中立评论数量 (一般)
    @Column(name = "opinion_number")
    private Integer opinionNumber;
    // 批评数量
    @Column(name = "criticism_number")
    private Integer criticismNumber; // 评分小于0
    // 表扬数量
    @Column(name = "praise_number")
    private Integer praiseNumber; //评分大于0
    // 中性评论数量
    @Column(name = "neutral_number")
    private Integer neutralNumber; // 评价目标不为其它，评分为0

    @Column(name = "nocare_number")
    private Integer nocareNumber; // 评价目标为其它，且评分为0
    /*
    @Column(name = "praise_response_number")
    private Integer praiseResponseNumber;
    @Column(name = "criticism_response_number")
    private Integer criticismResponseNumber;
    @Column(name = "neutral_response_number")
    private Integer neutralResponseNumber;
    @Column(name = "nocare_response_number")
    private Integer nocareResponseNumber;
    @Column(name = "is_in_main_bad")
    private Integer isInMainBad;
    */
}
