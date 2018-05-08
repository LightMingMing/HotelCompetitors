package com.competitors.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.khazix.core.ColumnIgnore;
import com.khazix.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hotel_comment")
public class Comment extends BaseEntity {

    private static final long serialVersionUID = 6888504517752415922L;
    private final static Logger logger = LoggerFactory.getLogger(Comment.class);

    private String hotelName;
    private String phone;
    private Integer webId;
    @ColumnIgnore
    private String platformName;
    private String hotelId;
    private String userName;
    @Column(name = "grade")
    private Float score;
    @Column(name = "comment_text")
    private String context;
    @Column(name = "comment_xml")
    private String XML;
    @ColumnIgnore
    private List<SentimentAnalysis> sentimentAnalysisList = new ArrayList<>();
    @Column(name = "comment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String response;
    /**
     * 0 表示无
     * 1 表示有
     */
    @ColumnIgnore
    @Column(name = "hasresponse")
    private Integer hasResponse;
    /**
     * 0 表示不是
     * 1 表示是
     */
    @ColumnIgnore
    @Column(name = "is_in_main")
    private Integer isInMain;
    /**
     * -1 无正向
     * 0 既有正向又有负向
     * 1 无负向
     */
    @Column(name = "good_or_bad")
    private Integer emotionStatus;
    /**
     * 0 不是
     * 1 是
     */
    @ColumnIgnore
    @Column(name = "is_in_main_bad")
    private Integer isInMainCritique;
    @Column(name = "hotel_grade")
    private Float hotelScore;

    public void sentimentAnalysis() {
        if (!StringUtils.isEmpty(XML)) {
            String source = "";
            try {
                // source = XML.replaceAll("\\s+", "");
                source = XML.replaceAll("[\r|\n]+", "");
                JSONArray jsonArray = new JSONArray(source);
                for (int i = 0; i < jsonArray.length(); i ++) {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    int sentimentValue = jsonObj.getInt("sentimentValue");
                    String[] targets = jsonObj.getString("target").split(" ");
                    String content = jsonObj.getString("text");
                    sentimentAnalysisList.add(new SentimentAnalysis(sentimentValue, targets, content));
                }
            } catch(JSONException excetion) {
                logger.error("id:{} source:{}", this.id, this.XML, excetion);
            }

        }
    }

    @Override
    public String toString() {
        return String.format("{id:%d, webId:%d, phone:%s, hotelName:%s, hotelId:%s, score:%f, date:%s}", id, webId, phone, hotelName, hotelId, score, date);
    }

    public static void main(String[] args) {
        // String source = "[{\"sentimentValue\":\"1\",\"target\":\"2 \",\"text\":\"位置佳，\"},{\"sentimentValue\":\"1\",\"target\":\"1 \",\"text\":\"房价实惠，\"},{\"sentimentValue\":\"1\",\"target\":\"4 \",\"text\":\"卫生良好\"}]";
        String source = "[{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"良く、\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"食事や\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"買い\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"物に\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"最適です。\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"部屋に\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"大型冷蔵庫があり\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"生ものの\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"買物にはとても\"},{\"sentimentValue\":\"1\",\"target\":\"0 \",\"text\":\"便利です。。。\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"部屋はとても\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"綺麗で、\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"住み\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"心地がよいです.\n" +
                "\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"客室が\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"良い\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"清潔感あり\"},{\"sentimentValue\":\"0\",\"target\":\"0 \",\"text\":\"物靜か\"}]";
        // System.out.println(source.replaceAll("\\n+", ""));
        System.out.println(source.replaceAll("[\n\r]+", ""));
        JSONArray jsonArray = new JSONArray(source.replaceAll("\n", ""));
        for (int i = 0; i < jsonArray.length(); i ++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            System.out.println(jsonObj);
            //System.out.println(jsonObj.getJSONObject("sentimentValue"));
            System.out.println(jsonObj.getInt("sentimentValue"));
            System.out.println(jsonObj.getString("target"));
            System.out.println(jsonObj.getString("text"));
        }
    }
}
