package com.competitors.service;

import com.competitors.domain.*;
import com.competitors.entity.HotelCompetitors;
import com.competitors.entity.HotelStandard;
import com.competitors.repository.CommentRepository;
import com.competitors.repository.DayAnalysisRepository;
import com.competitors.repository.DayAnalysisTargetRepository;
import com.competitors.repository.HotelRepository;
import com.competitors.repository.HotelCompetitorsRepository;
import com.competitors.support.DictionaryControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelServiceImpl implements HotelService, InitializingBean{

    private final static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private DayAnalysisTargetRepository dayAnalysisTargetRepository;
    @Autowired
    private DayAnalysisRepository dayAnalysisRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelCompetitorsRepository hotelCompetitorsRepository;

    @Autowired
    private DictionaryControl dictionaryControl;

    private Map<String, String> platformDictionary;
    private Map<String, String> analysisTargetDictionary;

    @Override
    public void afterPropertiesSet() {
        platformDictionary = dictionaryControl.getDictionary("platform").getItemsAsMap();
        analysisTargetDictionary = dictionaryControl.getDictionary("analysisTarget").getItemsAsMap();
    }

    @Override
    public PlatformAverageScore getPlatformAverageScore(String phone, Date beginDate, Date endDate, Integer webId) {
        PlatformAverageScore r = commentRepository.getPlatformAverageScore(phone, beginDate, endDate, webId);
        r.setPlatformName(platformDictionary.get(r.getPlatformId() + ""));
        return r;
    }

    @Override
    public List<PlatformAverageScore> getPlatformAverageScoreList(String phone, Date beginDate, Date endDate) {
        List<PlatformAverageScore> result = commentRepository.getPlatformAverageScoreList(phone, beginDate, endDate);
        for (PlatformAverageScore platformAverageScore : result) {
            platformAverageScore.setPlatformName(platformDictionary.get(platformAverageScore.getPlatformId() + ""));
        }
        return result;
    }

    @Override
    public SentimentDistribution getSentimentDistribution(String phone, Date beginDate, Date endDate) {
        return dayAnalysisRepository.getSentimentDistribution(phone, beginDate, endDate);
    }

    @Override
    public List<PlatformSentimentDistribution> getPlatformSentimentDistributionForEachPlatform(String phone, Date beginDate, Date endDate) {
        List<PlatformSentimentDistribution> platformSentimentDistributionList = dayAnalysisRepository.getPlatformSentimentDistributionForEachPlatform(phone, beginDate, endDate);
        for (PlatformSentimentDistribution psd : platformSentimentDistributionList) {
            psd.setPlatformName(platformDictionary.get("" + psd.getPlatformId()));
        }
        return platformSentimentDistributionList;
    }

    @Override
    public List<TargetSentimentDistribution> getTargetSentimentDistributionList(String phone, Date startDate, Date endDate) {
        List<TargetSentimentDistribution> result = dayAnalysisTargetRepository.getTargetSentimentDistributionList(phone, startDate, endDate);
        for (TargetSentimentDistribution t : result) {
            t.setAnalysisTargetName(analysisTargetDictionary.get(t.getAnalysisTarget() + ""));
        }
        return result;
    }

    @Override
    public int getCommentSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return dayAnalysisRepository.getCommentSum(phone, beginDate, endDate, webId);
    }

    @Override
    public List<PlatformCommentNumber> getCommentNumberForEachPlatform(String phone, Date beginDate, Date endDate) {
        return dayAnalysisRepository.getCommentNumberForEachPlatform(phone, beginDate, endDate);
    }

    @Override
    public int getPraiseSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return dayAnalysisRepository.getPraiseSum(phone, beginDate, endDate, webId);
    }

    @Override
    public int getCriticismSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return dayAnalysisRepository.getCriticismSum(phone, beginDate, endDate, webId);
    }

    @Override
    public int getOpinionSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return dayAnalysisRepository.getOpinionSum(phone, beginDate, endDate, webId);
    }

    @Override
    public int getNeutralSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return dayAnalysisRepository.getNeutralSum(phone, beginDate, endDate, webId);
    }

    @Override
    public List<String> getHotelCompetitorList(String phone) {
        HotelCompetitors hotelCompetitors = hotelCompetitorsRepository.get("phone", phone);
        return Arrays.asList(hotelCompetitors.getCompetitors().split(", "));
    }

}
