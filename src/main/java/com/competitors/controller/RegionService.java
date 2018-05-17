package com.competitors.controller;

import com.competitors.entity.Region;
import com.competitors.support.RegionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/region")
public class RegionService {

    @Autowired
    private RegionCache regionCache;

    @ResponseBody
    @GetMapping
    public List<Region> listProvince() {
        return regionCache.listProvince();
    }

    @ResponseBody
    @GetMapping("/{provinceCode}")
    public List<Region> listCity(@PathVariable String provinceCode){
        return regionCache.listCity(provinceCode);
    }

}
