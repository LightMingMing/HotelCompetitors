package com.competitors.controller;

import com.competitors.domain.PlatformCommentNumber;
import com.competitors.entity.HotelStandard;
import com.competitors.json.PlatformCommentStatisticsJ;
import com.competitors.repository.CommentRepository;
import com.competitors.repository.HotelRepository;
import com.competitors.service.HotelService;
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
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public String competitor(String phone, Model model) throws ParseException {
        model.addAttribute("phone", phone);
        HotelStandard source = hotelRepository.get("phone", phone);
        if (source != null) {
            List<HotelStandard> hotelList = hotelService.getHotelCompetitorList(phone);
            List<Integer> commentNumberList = new ArrayList<>(10);
            // Date date = DateUtils.parse("2000-01-01");
            // Date endDate = new Date();
            for (HotelStandard hotel : hotelList) {
                commentNumberList.add(hotelService.getCommentSum(hotel.getPhone()));
            }
            model.addAttribute("source", source);
            model.addAttribute("hotelList", hotelList);
            model.addAttribute("commentNumberList", commentNumberList);
        }
        return "competitor_list";
    }


    @ResponseBody
    @PostMapping(value = "/platformCommentStatistics", produces = "application/json; charset=utf-8")
    public String compare(@RequestBody List<String> phoneList, Model model) {
        PlatformCommentStatisticsJ commentStatisticsJ = new PlatformCommentStatisticsJ(phoneList.size());
        for(String phone : phoneList) {
            // logger.info(phone);
            HotelStandard hotel = hotelRepository.get("phone", phone);
            String name = hotel.getName();
            int left = name.indexOf('(');
            if (left > 0)
                name = name.substring(0, name.indexOf('('));
            List<PlatformCommentNumber> numberList = hotelService.getCommentNumberForEachPlatform(phone);
            commentStatisticsJ.addHotelData(name, numberList);
        }
         return commentStatisticsJ.toJsonString();
    }
}
