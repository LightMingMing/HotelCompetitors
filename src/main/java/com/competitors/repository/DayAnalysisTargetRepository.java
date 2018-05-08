package com.competitors.repository;

import com.competitors.domain.TargetSentimentDistribution;
import com.competitors.entity.DayAnalysisTarget;
import com.khazix.core.repository.ReadRepository;

import java.util.Date;
import java.util.List;

public interface DayAnalysisTargetRepository extends ReadRepository<DayAnalysisTarget> {
    // 每个评价的评价情况(好评数、差评数、中性评价)
    List<TargetSentimentDistribution> getTargetSentimentDistributionList(String phone, Date startDate, Date endDate);
}
