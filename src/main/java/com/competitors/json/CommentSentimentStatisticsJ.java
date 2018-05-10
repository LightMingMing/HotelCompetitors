package com.competitors.json;

import com.competitors.domain.SentimentDistribution;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentSentimentStatisticsJ {
    private List<String> categories;
    private int[][] data = new int[4][];

    public CommentSentimentStatisticsJ(int size) {
        this.categories = new ArrayList<>(size);
        for (int i = 0; i < 4; i ++)
            data[i] = new int[size];
    }

    public String toJsonString() {
        JSONArray series = new JSONArray();

        JSONObject n1 = new JSONObject(); n1.put("name", "好评"); n1.put("data", data[0]); series.put(n1);
        JSONObject n2 = new JSONObject(); n2.put("name", "中评"); n2.put("data", data[1]); series.put(n2);
        JSONObject n3 = new JSONObject(); n3.put("name", "差评"); n3.put("data", data[2]); series.put(n3);
        JSONObject n4 = new JSONObject(); n4.put("name", "其它"); n4.put("data", data[3]); series.put(n4);

        JSONObject result = new JSONObject();
        result.put("categories", categories);
        result.put("series", series);
        return result.toString();
    }

    public void addData(String name, SentimentDistribution sentiment) {
        int len = categories.size();
        categories.add(name);
        // JSONObject serie = new JSONObject();
        // serie.put("name", name);
        data[0][len] = sentiment.getPraiseNumber();
        data[1][len] = sentiment.getNeutralNumber();
        data[2][len] = sentiment.getCriticismNumber();
        data[3][len] = sentiment.getNocareNumber();
        // serie.put("data", n);
        // series.put(serie);
    }
}
