package com.competitors.repository.impl;

import com.competitors.domain.TargetSentiment;
import com.competitors.domain.TargetSentimentDistribution;
import com.competitors.entity.DayAnalysisTarget;
import com.competitors.repository.DayAnalysisTargetRepository;
import com.khazix.core.repository.ReadRepositoryImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("dayAnalysisTargetRepository")
public class DayAnalysisTargetRepositoryImpl extends ReadRepositoryImpl<DayAnalysisTarget> implements DayAnalysisTargetRepository {

    private static RowMapper<TargetSentiment> targetSentimentRowMapper = (rst, rowNum) -> {
        TargetSentiment targetSentiment = new TargetSentiment();
        targetSentiment.setAnalysisTarget(rst.getInt("score_target"));
        targetSentiment.setNumber(rst.getInt("number"));
        targetSentiment.setScore(rst.getInt("score"));
        return targetSentiment;
    };

    @Override
    public List<TargetSentimentDistribution> getTargetSentimentDistributionList(String phone, Date startDate, Date endDate) {
        String sql = "select score_target, score, sum(opinion_number) as number " +
                "from analysis_day_target where phone=? and analysis_date>=? and analysis_date<=? " +
                "GROUP BY score_target, score";
        //
        List<TargetSentiment> targetSentimentList = template.query(sql, new Object[]{phone, startDate, endDate}, targetSentimentRowMapper);
        List<TargetSentimentDistribution> list = new ArrayList<>(7);

        for (int i = 0; i < 7; i++) {
            TargetSentimentDistribution t = new TargetSentimentDistribution();
            t.setAnalysisTarget(i);
            list.add(t);
        }
        for(TargetSentiment targetSentiment : targetSentimentList) {
            int t = targetSentiment.getAnalysisTarget();
            int s = targetSentiment.getScore();
            int n = targetSentiment.getNumber();
            if (s == -1) list.get(t).setCriticismNumber(n);
            if (s == 0) list.get(t).setNeutralNumber(n);
            if (s == 1) list.get(t).setPraiseNumber(n);
        }
        return list;
    }

}
