package com.competitors.support;

import com.khazix.core.support.Dictionary;
import com.khazix.core.support.LabelValue;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class DictionaryControl {

    private final static Map<String, Dictionary> dictionaryMap = new HashMap<>(16);

    static {
        Dictionary analysisTarget = new Dictionary();
        analysisTarget.setName("analysisTarget");
        analysisTarget.setItems(Arrays.asList(
                new LabelValue("其它", "0"), new LabelValue("性价比", "1"), new LabelValue("地理位置", "2"),
                new LabelValue("服务质量", "3"), new LabelValue("环境卫生", "4"), new LabelValue("设备设施", "5"),
                new LabelValue("餐饮服务", "6")));

        Dictionary plateform = new Dictionary();
        plateform.setName("platform");
        plateform.setItems(Arrays.asList(
                new LabelValue("全部", "0"), new LabelValue("携程", "1"), new LabelValue("艺龙", "2"),
                new LabelValue("美团", "3"), new LabelValue("去哪儿", "4"), new LabelValue("阿里旅行", "5"),
                new LabelValue("大众点评", "6")));

        dictionaryMap.put(analysisTarget.getName(), analysisTarget);
        dictionaryMap.put(plateform.getName(), plateform);
    }

    public Dictionary getDictionary(String name) {
        return dictionaryMap.get(name);
    }
}
