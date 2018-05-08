package com.competitors.repository.impl;

import com.competitors.domain.PlatformSentimentDistribution;
import com.competitors.domain.SentimentDistribution;
import com.competitors.entity.DayAnalysis;
import com.competitors.repository.DayAnalysisRepository;
import com.khazix.core.repository.ReadRepositoryImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository("dayAnalysisRepository")
public class DayAnalysisRepositoryImpl extends ReadRepositoryImpl<DayAnalysis> implements DayAnalysisRepository {

    private static RowMapper<SentimentDistribution> sentimentDistributionMapper = (rst, rowNum) -> {
        SentimentDistribution sentimentDistribution = new SentimentDistribution();
        sentimentDistribution.setCriticismNumber(rst.getInt("criticismNumber"));
        sentimentDistribution.setNeutralNumber(rst.getInt("neutralNumber"));
        // sentimentDistribution.setOpinionNumber(rst.getInt("opinionNumber"));
        sentimentDistribution.setPraiseNumber(rst.getInt("praiseNumber"));
        sentimentDistribution.setNocareNumber(rst.getInt("nocareNumber"));
        sentimentDistribution.computeRate();
        return sentimentDistribution;
    };

    private static RowMapper<PlatformSentimentDistribution> platformSentimentDistributionRowMapper = (rst, rowNum) -> {
        PlatformSentimentDistribution platformSentimentDistribution = new PlatformSentimentDistribution();
        platformSentimentDistribution.setPlatformId(rst.getInt("webid"));
        SentimentDistribution sentimentDistribution = new SentimentDistribution();
        sentimentDistribution.setCriticismNumber(rst.getInt("criticismNumber"));
        sentimentDistribution.setNeutralNumber(rst.getInt("neutralNumber"));
        // sentimentDistribution.setOpinionNumber(rst.getInt("opinionNumber"));
        sentimentDistribution.setPraiseNumber(rst.getInt("praiseNumber"));
        sentimentDistribution.setNocareNumber(rst.getInt("nocareNumber"));
        sentimentDistribution.computeRate();
        platformSentimentDistribution.setSentimentDistribution(sentimentDistribution);
        return platformSentimentDistribution;
    };

    private int getItemSum(String columnName, String phone, Date beginDate, Date endDate, Integer webId) {
        String format = "select sum(%s) from " + getTableName() + " where phone=? and analysis_date>=? and analysis_date<=?";
        List<Object> params = new ArrayList<>();
        String sql = String.format(format, columnName);
        params.add(phone);
        params.add(beginDate);
        params.add(endDate);

        if (webId != null && webId > 0) {
            sql += " and webid = ?";
            params.add(webId);
        }
        return template.queryForObject(sql, params.toArray(), Integer.class);
    }

    @Override
    public int getPraiseSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return getItemSum("praise_number", phone, beginDate, endDate, webId);
    }

    @Override
    public int getCriticismSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return getItemSum("criticism_number", phone, beginDate, endDate, webId);
    }

    @Override
    public int getOpinionSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return getItemSum("opinion_number", phone, beginDate, endDate, webId);
    }

    @Override
    public int getNeutralSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return getItemSum("neutral_number", phone, beginDate, endDate, webId);
    }

    @Override
    public int getCommentSum(String phone, Date beginDate, Date endDate, Integer webId) {
        return getItemSum("comment_number", phone, beginDate, endDate, webId);
    }

    @Override
    public SentimentDistribution getSentimentDistribution(String phone, Date beginDate, Date endDate) {
        String sql = "select sum(praise_number) praiseNumber, sum(criticism_number) criticismNumber, " +
                "sum(neutral_number) neutralNumber,  sum(nocare_number) nocareNumber from " + getTableName() +
                " where phone=? and analysis_date>=? and analysis_date<=?";
        return template.queryForObject(sql, new Object[]{phone, beginDate, endDate}, sentimentDistributionMapper);

    }

    @Override
    public PlatformSentimentDistribution getPlatformSentimentDistribution(String phone, Date beginDate, Date endDate, Integer webId) {
        String sql = "select webid, sum(praise_number) praiseNumber, sum(criticism_number) criticismNumber, " +
                "sum(neutral_number) neutralNumber,  sum(nocare_number) nocareNumber from " + getTableName() +
                " where phone=? and analysis_date>=? and analysis_date<=? and webid=?";

        return template.queryForObject(sql, new Object[]{phone, beginDate, endDate, webId}, platformSentimentDistributionRowMapper);
    }

    @Override
    public List<PlatformSentimentDistribution> getPlatformSentimentDistributionForEachPlatform(String phone, Date beginDate, Date endDate) {
        String sql = "select webid, sum(praise_number) praiseNumber, sum(criticism_number) criticismNumber, " +
                "sum(neutral_number) neutralNumber,  sum(nocare_number) nocareNumber from " + getTableName() +
                " where phone=? and analysis_date>=? and analysis_date<=? group by webid";
        return template.query(sql, new Object[]{phone, beginDate, endDate}, platformSentimentDistributionRowMapper);

    }



}
