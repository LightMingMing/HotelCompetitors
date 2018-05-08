package com.competitors.service.support;

import com.competitors.entity.HotelStandard;
import com.khazix.core.support.Coordinate;

import java.util.Comparator;

public class HotelComparator implements Comparator<HotelStandard>{

    private Coordinate origin;

    public HotelComparator(HotelStandard refer) {
        this.origin = build(refer);
    }

    public HotelComparator(Coordinate origin) {
        this.origin = origin;
    }

    @Override
    public int compare(HotelStandard hotel1, HotelStandard hotel2) {
        Coordinate c1 = build(hotel1);
        Coordinate c2 = build(hotel2);
        Integer del1 = Math.abs(c1.distanceFrom(origin));
        Integer del2 = Math.abs(c2.distanceFrom(origin));
        return del1.compareTo(del2);
    }

    private static Coordinate build(HotelStandard hotel) {
        return new Coordinate(hotel.getLongitude(), hotel.getLatitude());
    }
}
