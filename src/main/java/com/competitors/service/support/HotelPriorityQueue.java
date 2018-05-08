package com.competitors.service.support;

import com.competitors.entity.HotelStandard;
import com.competitors.service.support.HotelComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

public class HotelPriorityQueue {

    private PriorityQueue<HotelStandard> queue;

    public HotelPriorityQueue(int initialCapacity, HotelStandard origin){
         queue = new PriorityQueue<>(initialCapacity, new HotelComparator(origin));
    }

    public void addAll(Collection<HotelStandard> hotelStandardCollection) {
        queue.addAll(hotelStandardCollection);
    }

    public List<HotelStandard> poll(int max) {
        List<HotelStandard> result = new ArrayList<>(max);
        HotelStandard curr;
        int num = 0;
        while ((curr = queue.poll()) != null && num ++ < 10) {
            result.add(curr);
        }
        return result;
    }
}
