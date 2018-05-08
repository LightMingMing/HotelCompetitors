package com.competitors.repository;

import com.competitors.entity.HotelStandard;
import com.competitors.domain.HotelResultPage;
import com.competitors.service.CommentService;
import com.competitors.service.HotelService;
import com.competitors.config.JUnit4Configuration;
import com.khazix.core.support.Coordinate;
import com.khazix.core.support.ResultPage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelServiceTest extends JUnit4Configuration {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DayAnalysisRepository dayAnalysisRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void find() {

        ResultPage<HotelStandard> resultPage = new HotelResultPage();
        HotelStandard entity = new HotelStandard();
        entity.setPhone("0827-5557755");
        resultPage.setEntity(entity);

        hotelRepository.list(resultPage);
        Collection<HotelStandard> results = resultPage.getResult();
        results.forEach(h -> System.out.println(h.getName()));
    }

    @Test
    public void findOne() {
        HotelStandard standard = hotelRepository.get("id", 8517);
        System.out.println(standard);
    }

    @Test
    public void listByDistance() throws ParseException {
        // 0731-88331111
        HotelStandard source = hotelRepository.get("phone", "010-87606097");
        System.out.println("酒店信息：");

        Date beginDate = sdf.parse("2017-01-01");
        Date endDate = sdf.parse("2018-01-01");
        println(source, beginDate, endDate);
        System.out.println("竞争对手：");
        // List<HotelStandard> result = hotelRepository.listByDist(source, 1000);
        List<HotelStandard> r2 = hotelService.getHotelCompetitorList(source.getPhone());
        System.out.println(r2);

        // result.forEach(h -> println(h, beginDate, endDate));
    }



    public  void println(HotelStandard hotelStandard, Date beginDate, Date endDate) {
        System.out.println(hotelStandard);
        System.out.println(hotelService.getCommentSum(hotelStandard.getPhone(), beginDate, endDate, null));
        System.out.println(hotelService.getOpinionSum(hotelStandard.getPhone(), beginDate, endDate, null));
        System.out.println(hotelService.getPraiseSum(hotelStandard.getPhone(), beginDate, endDate, null));
        System.out.println(hotelService.getCriticismSum(hotelStandard.getPhone(), beginDate, endDate, null));
        System.out.println(hotelService.getNeutralSum(hotelStandard.getPhone(), beginDate, endDate, null));

        // 各平台平均得分
        System.out.println(hotelService.getPlatformAverageScoreList(hotelStandard.getPhone(), beginDate, endDate));
        // 各平台情感分布
        // System.out.println(hotelService.getPlatformSentimentDistributionForEachPlatform(hotelStandard.getPhone(), beginDate, endDate));
        System.out.println(hotelService.getTargetSentimentDistributionList(hotelStandard.getPhone(), beginDate, endDate));
    }


    @Test
    public void distance() {
        Coordinate c1 = new Coordinate(116.47563626896456, 39.816492955134976);
        Coordinate c2 = new Coordinate(101.52803811179243, 25.066169201120545);
        Coordinate c3 = new Coordinate(110.33992958721342, 20.02593554685282);
        System.out.println(c2.distanceFrom(c1));
        System.out.println(c3.distanceFrom(c1));
    }

}
