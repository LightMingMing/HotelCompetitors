package com.competitors.controller;

import com.competitors.entity.HotelStandard;
import com.competitors.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/competitor")
public class CompetitorController {
    private final static Logger logger = LoggerFactory.getLogger(CompetitorController.class);

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public String competitor(String phone, Model model) {
        model.addAttribute("phone", phone);
        // model.addAttribute("controllerName", "competitor");
        List<HotelStandard> hotelList = hotelService.getHotelCompetitorList(phone);
        model.addAttribute("hotelList", hotelList);
        return "competitor_list";
    }
}
