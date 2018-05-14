package com.competitors.repository.impl;

import com.competitors.entity.HotelCompetitors;
import com.competitors.repository.HotelCompetitorsRepository;
import com.khazix.core.repository.CrudRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository("hotelCompetitorsRepository")
public class HotelCompetitorsRepositoryImpl extends CrudRepositoryImpl<HotelCompetitors> implements HotelCompetitorsRepository {
}
