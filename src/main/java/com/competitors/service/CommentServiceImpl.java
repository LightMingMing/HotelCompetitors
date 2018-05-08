package com.competitors.service;

import com.competitors.domain.PlatformAverageScore;
import com.competitors.entity.Comment;
import com.competitors.entity.SentimentAnalysis;
import com.competitors.repository.CommentRepository;
import com.competitors.support.DictionaryControl;
import com.khazix.core.support.Page;
import com.khazix.core.support.ResultPage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService, InitializingBean {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private DictionaryControl dictionaryControl;

    private Map<String, String> platformDictionary;
    private Map<String, String> analysisTargetDictionary;

    @Override
    public void afterPropertiesSet() {
        platformDictionary = dictionaryControl.getDictionary("platform").getItemsAsMap();
        analysisTargetDictionary = dictionaryControl.getDictionary("analysisTarget").getItemsAsMap();
    }

    private void dict(Page<Comment> resultPage) {
        Collection<Comment> commentList = resultPage.getResult();
        // 分析目标
        for (Comment comment : commentList) {
            comment.sentimentAnalysis();
            for (SentimentAnalysis analysis : comment.getSentimentAnalysisList()) {
                String[] targets = analysis.getTargets();
                String[] targetNames = new String[targets.length];
                for (int i = 0; i < targets.length; i++)
                    targetNames[i] = analysisTargetDictionary.get(targets[i]);
                analysis.setTargetNames(targetNames);
            }
        }
        // 平台
        // Map<String, String> platformDict= dictionaryControl.getDictionary("platform").getItemsAsMap();
        for (Comment comment : commentList)
            comment.setPlatformName(platformDictionary.get(comment.getWebId() + ""));
    }


    @Override
    public Page<Comment> list(Page<Comment> page) {
        Page<Comment> result = commentRepository.list(page);
        dict(result);
        return result;
    }


}
