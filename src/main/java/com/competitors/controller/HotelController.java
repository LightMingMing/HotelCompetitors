package com.competitors.controller;

import com.competitors.domain.HotelResultPage;
import com.competitors.entity.HotelStandard;
import com.competitors.repository.HotelRepository;
import com.khazix.core.support.ResultPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    private final static Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public String index() {
        return "forward:/hotel/1";
    }

    @GetMapping("/{page}")
    public String list(@PathVariable int page, Model map) {

        ResultPage<HotelStandard> resultPage = new ResultPage<>();
        resultPage.setPageNo(page);
        resultPage.setPageSize(15);
        resultPage.setControllerName("hotel");
        map.addAttribute("resultPage", hotelRepository.list(resultPage));
        return "hotel_list";
    }


    @PostMapping
    public String list(HotelResultPage resultPage, Model map) {
        /*
        if (resultPage.getEntity() != null) {
            logger.info("请求实体:{}", resultPage.getEntity());
        }
        */
        resultPage.setControllerName("hotel");
        map.addAttribute("resultPage", hotelRepository.list(resultPage));
        return "hotel_list";
    }

}
