package com.competitors.json;

import com.competitors.domain.PlatformAverageScore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 各平台得分统计
 */
public class PlatformScoreSpiderStatisticsJ {

    // private String[] categorie = new String[]{"携程", "艺龙", "美团", "去哪儿", "大众点评"};
    // 1, 3, 4, 6
    private String[] categorie = new String[]{"携程", "美团", "去哪儿", "大众点评"};

    private List<String> hotelNameList;
    private double[][] hotelScore;

    public PlatformScoreSpiderStatisticsJ(int size) {
        hotelNameList = new ArrayList<>(size);
        hotelScore = new double[size][];
        for (int i = 0; i < size; i ++) {
            hotelScore[i] = new double[4];
        }
    }

    public void addData(String name, List<PlatformAverageScore> platformAverageScoreList) {
        int size = hotelNameList.size();
        hotelNameList.add(name);
        for (PlatformAverageScore score : platformAverageScoreList) {
            int target = score.getPlatformId();
            if (target == 2) continue;
            hotelScore[size][target == 1 ? 0 : target == 6 ? 3 : target - 2] = score.getAvgScore();
        }
    }

    public String toJsonString() {
        JSONArray series = new JSONArray();
        int size = hotelNameList.size();
        for(int i = 0; i < size; i ++) {
            JSONObject serie = new JSONObject();
            serie.put("data", hotelScore[i]);
            serie.put("name", hotelNameList.get(i));
            series.put(serie);
        }
        JSONObject result = new JSONObject();
        result.put("categories", categorie);
        result.put("series", series);
        return result.toString();
    }
}
