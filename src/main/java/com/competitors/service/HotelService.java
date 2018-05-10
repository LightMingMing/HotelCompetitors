package com.competitors.service;

import com.competitors.domain.*;
import com.competitors.entity.HotelStandard;

import java.util.Date;
import java.util.List;

public interface HotelService {

    /**
     * 平台平均得分
     * @see com.competitors.repository.CommentRepository
     */
    PlatformAverageScore getPlatformAverageScore(String phone, Date beginDate, Date endDate, Integer webId);

    /**
     * 每个平台平均得分
     * @see com.competitors.repository.CommentRepository
     */
    List<PlatformAverageScore> getPlatformAverageScoreList(String phone, Date beginDate, Date endDate);

    /**
     * 评论数量
     * @see com.competitors.repository.DayAnalysisRepository
     */
    int getCommentSum(String phone, Date beginDate, Date endDate, Integer webId);

    default int getCommentSum(String phone) {
        return getCommentSum(phone, null, null, null);
    }

    List<PlatformCommentNumber> getCommentNumberForEachPlatform(String phone, Date beginDate, Date endDate);

    default List<PlatformCommentNumber> getCommentNumberForEachPlatform(String phone) {
        return getCommentNumberForEachPlatform(phone, null, null);
    }

    // 表扬
    int getPraiseSum(String phone, Date beginDate, Date endDate, Integer webId);
    // 批评
    int getCriticismSum(String phone, Date beginDate, Date endDate, Integer webId);
    // 观点
    int getOpinionSum(String phone, Date beginDate, Date endDate, Integer webId);
    // 中性
    int getNeutralSum(String phone, Date beginDate, Date endDate, Integer webId);

    /**
     * 总评论情感分布
     * @see com.competitors.repository.DayAnalysisRepository
     */
    SentimentDistribution getSentimentDistribution(String phone, Date beginDate, Date endDate);

    default SentimentDistribution getSentimentDistribution(String phone) {
        return getSentimentDistribution(phone, null, null);
    }

    /**
     * 各平台评论情感分布
     * @see com.competitors.repository.DayAnalysisRepository
     */
    List<PlatformSentimentDistribution> getPlatformSentimentDistributionForEachPlatform(String phone, Date beginDate, Date endDate);

    /**
     * 每个评价的评价情况(好评数、差评数、中性评价)
     * @see com.competitors.repository.DayAnalysisTargetRepository
     */
    List<TargetSentimentDistribution> getTargetSentimentDistributionList(String phone, Date beginDate, Date endDate);

    /**
     * 获取酒店竞争对手
     * @param phone 酒店电话
     * @return 酒店竞争对手集合
     */
    List<HotelStandard> getHotelCompetitorList(String phone);
}
