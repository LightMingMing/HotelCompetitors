package com.competitors.entity;

import com.khazix.core.ColumnIgnore;
import com.khazix.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Table(name = "t_hotel_competitors")
public class HotelCompetitors extends BaseEntity{
    private String phone;
    private String competitors;
}
