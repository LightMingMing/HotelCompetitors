package com.competitors.controller;

import com.competitors.domain.PlatformCommentNumber;
import com.competitors.domain.SentimentDistribution;
import com.competitors.domain.TargetSentimentDistribution;
import com.competitors.entity.HotelStandard;
import com.competitors.json.CommentSentimentStatisticsJ;
import com.competitors.json.PlatformCommentStatisticsJ;
import com.competitors.json.TargetSentimentStatisticsJ;
import com.competitors.repository.HotelRepository;
import com.competitors.service.HotelService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/competitor")
public class CompetitorController {
    private final static Logger logger = LoggerFactory.getLogger(CompetitorController.class);

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public String competitor(String phone, Model model) throws ParseException {
        model.addAttribute("phone", phone);
        HotelStandard source = hotelRepository.get("phone", phone);
        if (source != null) {
            /*
            List<HotelStandard> hotelList = hotelService.getHotelCompetitorList(phone);
            List<Integer> commentNumberList = new ArrayList<>(10);
            for (HotelStandard hotel : hotelList) {
                commentNumberList.add(hotelService.getCommentSum(hotel.getPhone()));
            }
            */
            model.addAttribute("source", source);
            /*
            model.addAttribute("hotelList", hotelList);
            model.addAttribute("commentNumberList", commentNumberList);
            */
        }
        return "competitor_list";
    }

    @ResponseBody
    @GetMapping(value = "/{phone}/{page}",produces = "application/json; charset=utf-8")
    public String competitors(@PathVariable("phone") String phone, @PathVariable("page") int page) {
        List<String> phoneList = hotelService.getHotelCompetitorList(phone);
        int size = phoneList.size();

        if (page < 1) page = 1;
        int endPage = (size - 1) / 10 + 1;
        if (page > endPage) page = endPage;

        int startIndex = (page - 1) * 10;
        int endIndex = startIndex + 10;
        if (endIndex > size) endIndex = size;

        JSONArray hotelArray = new JSONArray();
        for (int i = startIndex; i < endIndex; i ++) {
            // logger.info("phone:" + phoneList.get(i));
            HotelStandard hotel = hotelRepository.get("phone", phoneList.get(i));
            int commentNumber = hotelService.getCommentSum(hotel.getPhone());
            JSONObject hotelJ = new JSONObject();
            hotelJ.put("index", i);
            hotelJ.put("name", hotel.getName());
            hotelJ.put("phone", hotel.getPhone());
            hotelJ.put("address", hotel.getAddress());
            hotelJ.put("commentNumber", commentNumber);
            hotelArray.put(hotelJ);
        }
        JSONObject result = new JSONObject();
        result.put("currPage", page);
        result.put("totalPage", endPage);
        result.put("competitors", hotelArray);
        return result.toString();
    }

    @ResponseBody
    @PostMapping(value = "/platformCommentStatistics", produces = "application/json; charset=utf-8")
    public String platformCommentStatistics(@RequestBody List<String> phoneList) {
        PlatformCommentStatisticsJ commentStatisticsJ = new PlatformCommentStatisticsJ(phoneList.size());
        for(String phone : phoneList) {
            // logger.info(phone);
            HotelStandard hotel = hotelRepository.get("phone", phone);
            String name = hotel.getName();
            int left = name.indexOf('(');
            if (left > 0)
                name = name.substring(0, name.indexOf('('));
            List<PlatformCommentNumber> numberList = hotelService.getCommentNumberForEachPlatform(phone);
            commentStatisticsJ.addData(name, numberList);
        }
         return commentStatisticsJ.toJsonString();
    }

    @ResponseBody
    @PostMapping(value = "/commentSentimentStatistics", produces = "application/json; charset=utf-8")
    public String commentSentimentStatistics(@RequestBody List<String> phoneList) {
        CommentSentimentStatisticsJ commentSentimentStatisticsJ = new CommentSentimentStatisticsJ(phoneList.size());
        for (String phone : phoneList) {
            HotelStandard hotel = hotelRepository.get("phone", phone);
            String name = hotel.getName();
            int left = name.indexOf('(');
            if (left > 0)
                name = name.substring(0, name.indexOf('('));
            SentimentDistribution sentiment = hotelService.getSentimentDistribution(phone);
            commentSentimentStatisticsJ.addData(name, sentiment);
        }
        return commentSentimentStatisticsJ.toJsonString();
    }

    @ResponseBody
    @PostMapping(value = "/targetSentimentStatistics", produces = "application/json; charset=utf-8")
    public String targetSentimentStatics(String phone) {
        HotelStandard hotel = hotelRepository.get("phone", phone);
        if (hotel == null) return "";
        List<TargetSentimentDistribution> targetSentimentDistributionList = hotelService.getTargetSentimentDistributionList(phone);
        TargetSentimentStatisticsJ targetSentimentStatisticsJ = new TargetSentimentStatisticsJ();
        targetSentimentStatisticsJ.addData(targetSentimentDistributionList);

        return targetSentimentStatisticsJ.toJsonString();
    }
}
