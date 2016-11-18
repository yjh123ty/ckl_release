/**
 * Project Name:ckl
 * File Name:HotelRoomRecord.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;
import java.util.List;

import tech.youmu.ckl.constants.Global;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>Title:HotelRoomRecord</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月12日下午3:47:34</p>
 * <p>Description:一个酒店的房间记录</p>
 */
public class HotelRoomRecordInfo {
    
    private Long id;

    private String hotelName;
    
    private Date bookDate;
    
    private List<HotelRoom> hotelRooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    @JsonFormat(pattern=Global.DATE_TIME_FORMAT, timezone="GMT+8")
    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public List<HotelRoom> getHotelRooms() {
        return hotelRooms;
    }

    public void setHotelRooms(List<HotelRoom> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }

    public Integer getTotalRemainNum() {
        Integer total = 0;
        for (HotelRoom room : hotelRooms) {
            total += room.getRemainNum();
        }
        return total;
    }

    @Override
    public String toString() {
        final int maxLen = 10;
        return "HotelRoomRecord [id=" + id + ", hotelName=" + hotelName + ", bookDate=" + bookDate + ", hotelRooms=" + (hotelRooms != null ? hotelRooms.subList(0, Math.min(hotelRooms.size(), maxLen)) : null) + "]";
    }
}
