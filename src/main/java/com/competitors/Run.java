package com.competitors;

import com.competitors.analysis.AnalysisCompetitors;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

    public static void main(String[] args) {
        //
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        AnalysisCompetitors competitors = context.getBean("analysisCompetitors", AnalysisCompetitors.class);
        competitors.run();
        context.close();
    }
}
