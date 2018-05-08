package com.competitors.repository;

import com.competitors.entity.HotelStandard;
import com.khazix.core.repository.ReadRepository;

import java.util.List;

public interface HotelRepository extends ReadRepository<HotelStandard> {
    List<HotelStandard> listByDist(HotelStandard source, Integer distance);
    
}
