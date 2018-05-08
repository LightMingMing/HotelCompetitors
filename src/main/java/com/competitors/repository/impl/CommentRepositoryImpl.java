package com.competitors.repository.impl;

import com.competitors.domain.PlatformAverageScore;
import com.competitors.entity.Comment;
import com.competitors.repository.CommentRepository;
import com.khazix.core.repository.ReadRepositoryImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("commentRepository")
public class CommentRepositoryImpl extends ReadRepositoryImpl<Comment> implements CommentRepository {

    private static RowMapper<PlatformAverageScore> platfromAverageScoreRowMapper = (rst, rowNum)-> {
        PlatformAverageScore p = new PlatformAverageScore();
        p.setPlatformId(rst.getInt("webId"));
        p.setAvgScore(rst.getDouble("avgScore"));
        return p;
    };

    @Override
    public List<Comment> list(int startIndex, int maxResults) {
        String where = " where id >= (select id from " + getTableName() + " order by id limit ?, 1) limit ?";
        return template.query(getQuerySQL(where), new Object[]{startIndex, maxResults}, getRowMapper());
    }

    @Override
    public PlatformAverageScore getPlatformAverageScore(String phone, Date beginDate, Date endDate, Integer webId) {
        if (webId == null || webId < 0)
            throw new RuntimeException("平台ID不存在");
        String sql = "select webid, ifnull(round(sum(grade)/count(*), 2), 0) avgScore from " + getTableName() + " where phone=? and comment_time>=? and comment_time<=? and webId=?";
        return template.queryForObject(sql, new Object[] {phone, beginDate, endDate, webId}, platfromAverageScoreRowMapper);
    }

    @Override
    public List<PlatformAverageScore> getPlatformAverageScoreList(String phone, Date beginDate, Date endDate) {
        String sql = "select webid, ifnull(round(sum(grade)/count(*), 2), 0) avgScore from " + getTableName() + " where phone=? and comment_time>=? and comment_time<=? group by webid";
        return template.query(sql, new Object[] {phone, beginDate, endDate}, platfromAverageScoreRowMapper);
    }

    @Override
    public List<String> getHotelPhoneListInCommentRange(int min, int max) {
        String sql = "select phone from (select phone, count(phone) as comment_number from " + getTableName() + " group by phone) as t where t.comment_number >= ? and t.comment_number <= ?";
        return template.queryForList(sql, new Object[]{min, max}, String.class);
    }
}
