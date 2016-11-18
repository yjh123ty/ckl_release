/**
 * Project Name:ckl
 * File Name:HotelRoomRecordComponent.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.domain.HotelRoom;
import tech.youmu.ckl.domain.HotelRoomRecord;
import tech.youmu.ckl.service.IHotelRoomRecordService;
import tech.youmu.ckl.service.IHotelRoomService;

/**
 * <p>Title:HotelRoomRecordComponent</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月9日下午5:52:38</p>
 * <p>Description:
 *      酒店房间的记录自动生成
 *      每一天 1:00 生成所有 酒店房间记录
 *      方便用户预订
 * </p>
 */
@Component
public class HotelRoomRecordComponent {
    
    @Autowired
    private IHotelRoomRecordService hotelRoomRecordService;
    
    @Autowired
    private IHotelRoomService hotelRoomService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月26日上午10:25:39;</p>
     *	<p>Description: 初始化记录数据 生成两个月内的房间记录数量;</p>
     */
    public void initHotelRoomRecord(){
        List<HotelRoomRecord> hotelRoomRecords = new ArrayList<>();
        List<HotelRoom> allRoom = hotelRoomService.getAll();
        // 遍历之后两个月的所有房间   是否有房间预订记录  如果有 则 不做操作 如果没有则添加一个空的房间记录
        Date startDate = new Date();
        Date endDate = DateUtils.addMonths(startDate, 2);
        ReadableInstant start = new DateTime(startDate);
        ReadableInstant end = new DateTime(endDate);
        int days = Days.daysBetween(start, end).getDays();
        for (HotelRoom hotelRoom : allRoom) {
            for (int i = 0; i <= days; i++) {
                HotelRoomRecord record = new HotelRoomRecord(0, 0, DateUtils.addDays(startDate, i), hotelRoom);
                hotelRoomRecords.add(record);
            }
        }
        if(hotelRoomRecords.size() > 0) {
            hotelRoomRecordService.saveIfNotExist(hotelRoomRecords);
        }
    }
    
    /**
     * 1.查询所有的酒店 id
     * 2.遍历所有酒店 id 根据酒店的记录表中的数据
     * 3.生成每个酒店房间两个月后 多一天的房间数量记录
     */
    @Scheduled(cron="0 0 1 * * ?")
    public void createHotelRoomRecord(){
        List<HotelRoomRecord> hotelRoomRecords = new ArrayList<>();
        Date bookDate = DateUtils.addDays(DateUtils.addMonths(new Date(), 2), 1);
        List<HotelRoom> allRoom = hotelRoomService.getAll();
        for (HotelRoom hotelRoom : allRoom) {
            HotelRoomRecord record = new HotelRoomRecord(0, 0, bookDate, hotelRoom);
            hotelRoomRecords.add(record);
        }
        if(hotelRoomRecords.size() > 0) {
            hotelRoomRecordService.saveIfNotExist(hotelRoomRecords);
        }
    }

    
//    public static void main(String[] args) {
//        Date startDate = new Date();
//        Date endDate = DateUtils.addMonths(startDate, 2);
//        ReadableInstant start = new DateTime(startDate);
//        ReadableInstant end = new DateTime(endDate);
//        int days = Days.daysBetween(start, end).getDays();
//        System.out.println(days);
//        System.out.println("end month " + endDate.toLocaleString() + " end day " + DateUtils.addDays(startDate, days).toLocaleString());
//    }
}
