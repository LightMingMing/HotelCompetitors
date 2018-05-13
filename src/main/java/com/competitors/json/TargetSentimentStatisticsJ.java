package com.competitors.json;

import com.competitors.domain.TargetSentimentDistribution;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class TargetSentimentStatisticsJ {
    // target = 1 - 6 0
    private List<String> categorieList = Arrays.asList("性价比", "地理位置", "服务质量", "环境卫生", "设备设施", "餐饮服务", "其它");
    private String[] serieNameArray = new String[] {"表扬", "中评", "批评"};
    private int[][] serieArray = new int[3][];

    public TargetSentimentStatisticsJ() {
        for (int i = 0; i < 3; i ++) serieArray[i] = new int[7];
    }

    public void addData(List<TargetSentimentDistribution> data) {
        for (TargetSentimentDistribution distribution : data) {
            int index = index(distribution.getAnalysisTarget());
            serieArray[0][index] = distribution.getPraiseNumber();
            serieArray[1][index] = distribution.getNeutralNumber();
            serieArray[2][index] = distribution.getCriticismNumber();
        }
    }

    private int index(int target) {
        return target == 0 ?  6 : target - 1;
    }

    public String toJsonString() {
        JSONArray series = new JSONArray();
        for(int i = 0; i < 3; i ++) {
            JSONObject serie = new JSONObject();
            serie.put("name", serieNameArray[i]);
            serie.put("data", serieArray[i]);
            series.put(serie);
        }
        JSONObject result = new JSONObject();
        result.put("categories", categorieList);
        result.put("series", series);

        return result.toString();
    }
}
