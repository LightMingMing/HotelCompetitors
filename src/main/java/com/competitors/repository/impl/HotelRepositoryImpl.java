package com.competitors.repository.impl;
import com.competitors.entity.HotelStandard;
import com.competitors.repository.HotelRepository;
import com.khazix.core.repository.ReadRepositoryImpl;
import com.khazix.core.support.Coordinate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("hotelRepository")
public class HotelRepositoryImpl extends ReadRepositoryImpl<HotelStandard> implements HotelRepository {

    @Override
    public List<HotelStandard> listByDist(HotelStandard source, Integer distance) {

        double latDis = distance * Coordinate.LATITUDE_DEGREE_PER_METRE;
        double lonDis = distance * Coordinate.LONGITUDE_DEGREE_PER_METRE;
        double latitude = source.getLatitude(); // 伟度
        double longitude = source.getLongitude(); // 经度

        /*
        Object[] queryParams = new Object[6];
        queryParams[0] = source.getProvinceCode();
        queryParams[1] = source.getCityCode();
        queryParams[2] = longitude - degree;
        queryParams[3] = longitude + degree;
        queryParams[4] = latitude - degree;
        queryParams[5] = latitude + degree;
        String querySQL = "select * from " + getTableName() +
                " where province_code = ? and town_code = ? and coord_x >= ? and coord_x <= ? and coord_y >= ? and coord_y <= ?";
        */
        Object[] queryParams = new Object[4];
        queryParams[0] = longitude - lonDis;
        queryParams[1] = longitude + lonDis;
        queryParams[2] = latitude - latDis;
        queryParams[3] = latitude + latDis;
        String querySQL = getQuerySQL(" where coord_x > ? and coord_x < ? and coord_y > ? and coord_y < ?");

        List<HotelStandard> queryResult = template.query(querySQL, queryParams, getRowMapper());
        List<HotelStandard> result = new ArrayList<>();

        Coordinate c1 = new Coordinate(longitude, latitude);
        for (HotelStandard hotelStandard : queryResult) {
            if (!hotelStandard.getId().equals(source.getId()) && isWithinScope(c1, new Coordinate(hotelStandard.getLongitude(), hotelStandard.getLatitude()), distance)) {
                result.add(hotelStandard);
            }
        }
        return result;
    }

    private boolean isWithinScope(Coordinate c1, Coordinate c2, int distance) {
        return c1.distanceFrom(c2) < distance;
    }

    @Override
    public List<HotelStandard> list(int startIndex, int maxResults) {
        String where = " where phone >= (select phone from " + getTableName() + " order by phone limit ?, 1) limit ?";
        return template.query(getQuerySQL(where), new Object[]{startIndex, maxResults}, getRowMapper());
    }

    @Override
    public List<HotelStandard> listByPhones(List<String> phoneList) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(template);
        String where = " where phone in (:phoneList)";
        return namedParameterJdbcTemplate.query(getQuerySQL(where), Collections.singletonMap("phoneList", phoneList), getRowMapper());
    }
}