package com.competitors.repository;

import com.competitors.domain.PlatformAverageScore;
import com.competitors.domain.PlatformCommentNumber;
import com.competitors.entity.Comment;
import com.khazix.core.repository.ReadRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends ReadRepository<Comment> {
    PlatformAverageScore getPlatformAverageScore(String phone, Date beginDate, Date endDate, Integer webId);
    List<PlatformAverageScore> getPlatformAverageScoreList(String phone, Date beginDate, Date endDate);
    List<String> getHotelPhoneListInCommentRange(int min, int max);
    Integer getCommentNumber(String phone, Integer webId);
    /* List<PlatformCommentNumber> getCommentNumberForEachPlatform(String phone);*/
}