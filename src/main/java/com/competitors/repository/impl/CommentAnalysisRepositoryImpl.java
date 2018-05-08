package com.competitors.repository.impl;

import com.competitors.entity.CommentAnalysis;
import com.competitors.repository.CommentAnalysisRepository;
import com.khazix.core.repository.ReadRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentAnalysisRepository")
public class CommentAnalysisRepositoryImpl extends ReadRepositoryImpl<CommentAnalysis> implements CommentAnalysisRepository {

    @Override
    public List<CommentAnalysis> list(int commentId) {
        String querySQL = "select * from `hotel_score` where comment_id = ?";
        return template.query(querySQL, new Object[]{commentId}, getRowMapper());
    }



}
