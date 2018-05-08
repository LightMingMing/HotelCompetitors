package com.competitors.repository;

import com.competitors.domain.PlatformSentimentDistribution;
import com.competitors.domain.SentimentDistribution;
import com.competitors.entity.DayAnalysis;
import com.khazix.core.repository.ReadRepository;

import java.util.Date;
import java.util.List;

public interface DayAnalysisRepository extends ReadRepository<DayAnalysis>{
    int getCommentSum(String phone, Date beginDate, Date endDate, Integer webId);
    int getPraiseSum(String phone, Date beginDate, Date endDate, Integer webId);
    int getCriticismSum(String phone, Date beginDate, Date endDate, Integer webId);
    int getOpinionSum(String phone, Date beginDate, Date endDate, Integer webId);
    int getNeutralSum(String phone, Date beginDate, Date endDate, Integer webId);

    SentimentDistribution getSentimentDistribution(String phone, Date beginDate, Date endDate);
    PlatformSentimentDistribution getPlatformSentimentDistribution(String phone, Date beginDate, Date endDate, Integer webId);
    List<PlatformSentimentDistribution> getPlatformSentimentDistributionForEachPlatform(String phone, Date beginDate, Date endDate);
}
