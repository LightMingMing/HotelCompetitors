package com.competitors.json;

import com.competitors.domain.PlatformAverageScore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 各平台得分统计
 */
public class PlatformScoreStatisticsJ {

    // 酒店名集合
    private List<String> categorieList;

    private String[] platform = new String[]{"携程", "艺龙", "美团", "去哪儿", "大众点评"};
    private double[][] platformScore = new double[5][];

    public PlatformScoreStatisticsJ(int size) {
        categorieList = new ArrayList<>(size);
        for (int i = 0; i < 5; i ++) {
            platformScore[i] = new double[size];
        }
    }

    private int getIndex(int target) {
        return target == 6 ? 4 : target - 1;
    }

    public void addData(String name, List<PlatformAverageScore> platformAverageScoreList) {
        int size = categorieList.size();
        categorieList.add(name);
        for (PlatformAverageScore score : platformAverageScoreList) {
            int target = score.getPlatformId();
            platformScore[target == 6 ? 4 : target - 1][size] = score.getAvgScore();
            // if (target == 1) platformScore[0][size] = score.getAvgScore();
            // if (target == 2) platformScore[1][size] = score.getAvgScore();
            // if (target == 3) platformScore[2][size] = score.getAvgScore();
            // if (target == 4) platformScore[3][size] = score.getAvgScore();
            // if (target == 6) platformScore[4][size] = score.getAvgScore();
        }
    }

    public String toJsonString() {
        JSONArray series = new JSONArray();
        for(int i = 0; i < 5; i ++) {
            JSONObject serie = new JSONObject();
            serie.put("data", platformScore[i]);
            serie.put("name", platform[i]);
            series.put(serie);
        }
        JSONObject result = new JSONObject();
        result.put("categories", categorieList);
        result.put("series", series);
        return result.toString();
    }
}
