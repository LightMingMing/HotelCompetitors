package com.competitors.support;

import com.competitors.entity.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RegionCache implements InitializingBean {

    private volatile Map<String, Region> regionCache = new HashMap<>(20000);

    private final RowMapper<Region> regionRowMapper = (resultSet, index) -> {
        Region region = new Region();
        region.setId(resultSet.getInt(1));
        region.setCode(resultSet.getString(2));
        region.setParentCode(resultSet.getString(3));
        region.setName(resultSet.getString(4));
        region.setLevel(resultSet.getInt(5));
        region.setLast(resultSet.getBoolean(6));
        region.setPhoneCode(resultSet.getString(7));
        if (region.getLevel() > 1)
            region.setParent(regionCache.get(region.getParentCode()));
        regionCache.put(region.getCode(), region);
        return null;
    };

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate template;

    @Transactional(readOnly = true)
    private void cacheBuild() {
        template.query("select * from t_area", new Object[0], regionRowMapper);
    }

    public Region get(String areaCode) {
        logger.debug("根据区域码'{}'查询区域", areaCode);
        if (regionCache.isEmpty()) {
            synchronized (this) {
                if (regionCache.isEmpty())
                    cacheBuild();
            }
        }
        return regionCache.get(areaCode);
    }

    public List<Region> listProvince() {
        List<Region> result = new ArrayList<Region>(34);
        for (Region region : regionCache.values()) {
            if (region.getLevel() == 1) {
                result.add(region);
            }
        }
        return result;
    }

    public List<Region> listCity(String provinceCode) {
        Region province = get(provinceCode);
        List<Region> result = new ArrayList<Region>(34);
        for (Region region : regionCache.values()) {
            if (region.getParent() == province) {
                result.add(region);
            }
        }
        return result;
    }

    public List<Region> listCity() {
        List<Region> result = new ArrayList<>(128);
        /*
        for (Region region : regionCache.values()) {
            if (region.getLevel() == 2)
                result.add(region);
        }*/
        regionCache.values().stream().filter(r -> r.getLevel() == 2).forEach(result::add);
        return result;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        cacheBuild();
    }
}
