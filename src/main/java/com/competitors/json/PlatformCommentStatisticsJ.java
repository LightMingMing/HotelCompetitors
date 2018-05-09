package com.competitors.json;

import com.competitors.domain.PlatformCommentNumber;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlatformCommentStatisticsJ {

    private List<String> categorieList;

    private String[] platform = new String[]{"携程", "艺龙", "美团", "去哪儿", "大众点评"};
    private int[][] c = new int[5][];

    public PlatformCommentStatisticsJ(int size) {
        categorieList = new ArrayList<>(size);
        /*c = new Integer[size];
        l = new Integer[size];
        m = new Integer[size];
        q = new Integer[size];
        d = new Integer[size];*/
        for (int i = 0; i < 5; i ++) {
            c[i] = new int[size];
        }
    }

    public void addHotelData(String name, List<PlatformCommentNumber> numbers) {
        int size = categorieList.size();
        categorieList.add(name);
        for (PlatformCommentNumber number : numbers) {
            if (number.getTarget() == 1) c[0][size] = number.getNumber();
            if (number.getTarget() == 2) c[1][size] = number.getNumber();
            if (number.getTarget() == 3) c[2][size] = number.getNumber();
            if (number.getTarget() == 4) c[3][size] = number.getNumber();
            if (number.getTarget() == 6) c[4][size] = number.getNumber();
        }
        /*c[size] = numbers.get(0);
        l[size] = numbers.get(1);
        m[size] = numbers.get(2);
        q[size] = numbers.get(3);
        d[size] = numbers.get(4);*/
    }

    public String toJsonString() {
        JSONArray series = new JSONArray();
        for(int i = 0; i < 5; i ++) {
            JSONObject serie = new JSONObject();
            serie.put("name", platform[i]);
            serie.put("data", c[i]);
            series.put(serie);
        }

        JSONObject result = new JSONObject();
        result.put("categories", categorieList);
        result.put("series", series);
        return result.toString();
    }
}
