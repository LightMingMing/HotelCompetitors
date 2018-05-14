package com.competitors.analysis;

import com.competitors.analysis.support.*;
import com.competitors.entity.HotelCompetitors;
import com.competitors.entity.HotelStandard;
import com.competitors.repository.CommentRepository;
import com.competitors.repository.HotelCompetitorsRepository;
import com.competitors.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component("analysisCompetitors")
public class AnalysisCompetitors {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelCompetitorsRepository competitorsRepository;
    @Autowired
    private CommentRepository commentRepository;

    private Map<String, HotelStandard> hotelMap = new HashMap<>(10240);

    private void analysis(String phone) {
        if (competitorsRepository.exist("phone", phone)) return;
        HotelStandard origin = hotelRepository.get("phone", phone);
        int commentNumber = commentRepository.getCommentNumber(phone, null);

        List<String> phoneList;
        if (commentNumber < 10) {  // 664
            phoneList = commentRepository.getHotelPhoneListInCommentRange(0, 10);
        } else if (commentNumber < 50) { // 2000
            phoneList = commentRepository.getHotelPhoneListInCommentRange(10, 50);
        } else if (commentNumber < 100) { // 1288
            phoneList = commentRepository.getHotelPhoneListInCommentRange(50, 100);
        } else if (commentNumber < 200) { // 1500
            phoneList = commentRepository.getHotelPhoneListInCommentRange(100, 200);
        } else if (commentNumber < 400) { // 1278
            phoneList = commentRepository.getHotelPhoneListInCommentRange(200, 400);
        } else if (commentNumber < 1000) { // 813
            phoneList = commentRepository.getHotelPhoneListInCommentRange(400, 1000);
        } else if (commentNumber < 2000) {  // 159
            phoneList = commentRepository.getHotelPhoneListInCommentRange(1000, 2000);
        } else {
            phoneList = commentRepository.getHotelPhoneListInCommentRange(2000, 100000);
        }
        phoneList.remove(phone); // 过滤原酒店

        List<HotelStandard> hotelList = new ArrayList<>(phoneList.size());
        for(String each : phoneList) {
            hotelList.add(hotelMap.get(each));
        }

        HotelPriorityQueue queue = new HotelPriorityQueue(128, origin);
        queue.addAll(hotelList);
        List<HotelStandard> competitorList = queue.poll(100);

        insert(phone, competitorList);
    }

    private void insert(String phone, List<HotelStandard> competitorList) {
        StringBuilder builder = new StringBuilder();
        int size = competitorList.size();
        for (int i = 0; i < size - 1; i ++) {
            builder.append(competitorList.get(i).getPhone()).append(", ");
        }
        builder.append(competitorList.get(size - 1).getPhone());

        HotelCompetitors c = new HotelCompetitors();
        c.setCompetitors(builder.toString());
        c.setPhone(phone);
        competitorsRepository.save(c);
    }

    public void run() {
        List<HotelStandard> hotelStandardList = hotelRepository.findAll("phone", "longitude", "latitude");
        for (HotelStandard hotel : hotelStandardList)
            hotelMap.put(hotel.getPhone(), hotel);
        hotelStandardList.forEach(h -> analysis(h.getPhone()));
    }
}
