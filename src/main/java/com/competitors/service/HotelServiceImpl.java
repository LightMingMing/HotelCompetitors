package com.competitors.service;

import com.competitors.domain.*;
import com.competitors.entity.HotelStandard;
import com.competitors.repository.CommentRepository;
import com.competitors.repository.DayAnalysisRepository;
import com.competitors.repository.DayAnalysisTargetRepository;
import com.competitors.repository.HotelRepository;
import com.competitors.service.support.HotelPriorityQueue;
import com.competitors.support.DictionaryControl;
import com.khazix.core.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    public List<HotelStandard> getHotelCompetitorList(String phone) {
        Date beginDate = null;
        try {
            beginDate = DateUtils.parse("2000-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        HotelStandard origin = hotelRepository.get("phone", phone);
        int commentNumber = dayAnalysisRepository.getCommentSum(phone, beginDate, new Date(), null);
        logger.info("酒店'{}'评论数量：{}", phone, commentNumber);
        List<String> phoneList;
        List<HotelStandard> hotelList;
        if (commentNumber < 10) {  // 664
            phoneList = commentRepository.getHotelPhoneListInCommentRange(0, 10);
        } else if (commentNumber < 50) { // 2000
            phoneList = commentRepository.getHotelPhoneListInCommentRange(10, 50);
        } else if (commentNumber < 100) { // 1288
            phoneList = commentRepository.getHotelPhoneListInCommentRange(50, 100);
        } else if (commentNumber < 200) { // 1500
            phoneList = commentRepository.getHotelPhoneListInCommentRange(100, 200);
        } else if (commentNumber < 400) { // 1278
            phoneList = commentRepository.getHotelPhoneListInCommentRange(200, 400);
        } else if (commentNumber < 1000) { // 813
            phoneList = commentRepository.getHotelPhoneListInCommentRange(400, 1000);
        } else if (commentNumber < 2000){  // 159
            phoneList = commentRepository.getHotelPhoneListInCommentRange(1000, 2000);
        } else if (commentNumber < 3000){ // 28
            phoneList = commentRepository.getHotelPhoneListInCommentRange(2000, 3000);
        } else { // 6
            phoneList = commentRepository.getHotelPhoneListInCommentRange(3000, 100000);
        }
        phoneList.remove(phone); // 过滤原酒店
        logger.info("phoneList size:{}", phoneList.size());
        hotelList = hotelRepository.listByPhones(phoneList);
        // logger.info("竞争对手个数：{}", hotelList.size());

        HotelPriorityQueue queue = new HotelPriorityQueue(128, origin);
        queue.addAll(hotelList);
        return queue.poll(10);
    }

}
