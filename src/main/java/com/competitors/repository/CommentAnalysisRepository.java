package com.competitors.repository;

import com.competitors.entity.CommentAnalysis;
import com.khazix.core.repository.ReadRepository;

import java.util.List;

public interface CommentAnalysisRepository extends ReadRepository<CommentAnalysis> {
    List<CommentAnalysis> list(int commentId);
}
