package com.competitors.entity;

import com.khazix.core.ColumnIgnore;
import com.khazix.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class DayAnalysisTarget extends BaseEntity {
    private static final long serialVersionUID = -5938821216669411047L;
    private String phone;
    private String hotelId;
    @Column(name = "analysis_date")
    private Date analysisDate;
    private Integer webId;
    private String score;
    @Column(name = "score_target")
    private Integer analysisTarget;
    @ColumnIgnore
    private String analysisTargetName;
    // 意见数量
    @Column(name = "opinion_number")
    private Integer opinionNumber;
}
