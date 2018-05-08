package com.competitors.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khazix.core.ColumnIgnore;
import com.khazix.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
// @Table(name = "t_hotel_standard_new_copy")
@Table(name = "t_hotel_customer")
public class HotelStandard extends BaseEntity {

    private static final long serialVersionUID = 5395168054942993492L;

    @Column(name = "hotel_code")
    private String code;
    @Column(name = "hotel_name")
    private String name;
    private String phone;
    private String mobile;
    @JsonIgnore
    private String address;
    @Column(name = "coord_x")
    private Double longitude; // 经度
    @Column(name = "coord_y")
    private Double latitude; // 纬度
    @JsonIgnore
    @ColumnIgnore
    private Region region;

    @Column(name="province_code")
    private String provinceCode;
    @Column(name="town_code")
    private String cityCode;
    @Column(name = "area_code")
    private String areaCode;

    /*
    @JsonIgnore
    private String classifyCode;
    @JsonIgnore
    private String starLevel;
    @JsonIgnore
    private String businessCode;
    */

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
