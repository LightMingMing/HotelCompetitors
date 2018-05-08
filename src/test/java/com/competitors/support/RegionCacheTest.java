package com.competitors.support;

import com.competitors.entity.Region;
import com.competitors.support.RegionCache;
import com.competitors.config.JUnit4Configuration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RegionCacheTest extends JUnit4Configuration {

    @Autowired
    private RegionCache regionCache;

    @Test
    public void getTest() {
        assertNotNull(regionCache.get("110000"));
        assertEquals(regionCache.get("110100").getName(), "北京");
    }

    @Test
    public void printAllCity() {
        List<Region> region = regionCache.listCity();
        // region.forEach(r -> System.out.print(r.getName() + ", "));

        List<String> result = new ArrayList<>();
        region.forEach(r -> result.add(r.getParent().getName() + " " + r.getName()));
        Collections.sort(result);
        result.forEach(System.out::println);
    }
}