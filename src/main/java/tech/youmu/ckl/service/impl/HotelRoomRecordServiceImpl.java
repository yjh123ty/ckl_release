/**
 * Project Name:ckl
 * File Name:HotelRoomRecordServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.HotelRoomRecord;
import tech.youmu.ckl.domain.HotelRoomRecordInfo;
import tech.youmu.ckl.exception.LogicException;
import tech.youmu.ckl.mapper.HotelRoomRecordMapper;
import tech.youmu.ckl.query.HotelRoomRecordQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IHotelRoomRecordService;

/**
 * <p>Title:HotelRoomRecordServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月12日上午10:36:01</p>
 * <p>Description:TODO</p>
 */
@Service
public class HotelRoomRecordServiceImpl implements IHotelRoomRecordService {
    
    @Autowired
    private HotelRoomRecordMapper hotelRoomRecordMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomRecordService#getPageList(tech.youmu.ckl.query.HotelRoomRecordQuery)
     */
    @Override
    public Map<String, Object> getPageList(HotelRoomRecordQuery query) {
        Map<String, Object> result = new HashMap<String, Object>();
        long count = hotelRoomRecordMapper.getCountByQuery(query);
        if(count>0){
            long totalPage = count % query.getPageSize() == 0 ? count / query.getPageSize() : count / query.getPageSize() + 1;
            result.put("total", count);
            result.put("totalPage", totalPage);
            result.put("currentPage", query.getCurrentPage());
            result.put("rows", hotelRoomRecordMapper.getByQuery(query));
        }
        return result;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomRecordService#addOfflineRoomNum(java.lang.Long)
     */
    @Override
    public boolean addOfflineRoomNum(Long recordId) {
        // 判断房间的数量是否大于总的房间数量
        int rows = hotelRoomRecordMapper.addOfflineRoomNum(recordId);
        if(rows == 1){
            return true;
        }else if(rows == 0) {
            return false;
        }else {
            throw new RuntimeException();
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomRecordService#removeOfflineRoomNum(java.lang.Long)
     */
    @Override
    public boolean removeOfflineRoomNum(Long recordId) {
       int rows = hotelRoomRecordMapper.removeOfflineRoomNum(recordId);
       if(rows == 1){
           return true;
       }else if(rows == 0) {
           return false;
       }else {
           throw new RuntimeException();
       }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomRecordService#save(tech.youmu.ckl.domain.HotelRoomRecord)
     */
    @Override
    public void save(HotelRoomRecord hotelRoomRecord) {
        hotelRoomRecordMapper.save(hotelRoomRecord);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomRecordService#saveIfNotExist(tech.youmu.ckl.domain.HotelRoomRecord)
     */
    @Override
    public void saveIfNotExist(HotelRoomRecord record) {
       //　查询这一天的预订记录是否存在 
       long count = hotelRoomRecordMapper.existRecordCount(record.getRoom().getId(), record.getBookDate());
       // 如果不存在
       if(count == 0) {
           save(record);
       }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomRecordService#saveIfNotExist(java.util.List)
     */
    @Override
    public void saveIfNotExist(List<HotelRoomRecord> hotelRoomRecords) {
        List<HotelRoomRecord> saveRecords = new ArrayList<HotelRoomRecord>();
        for (HotelRoomRecord hotelRoomRecord : hotelRoomRecords) {
            long count = hotelRoomRecordMapper.existRecordCount(hotelRoomRecord.getRoom().getId(), hotelRoomRecord.getBookDate());
            if(count == 0){
                saveRecords.add(hotelRoomRecord);
            }
        }
        if(saveRecords.size() > 0) {
            hotelRoomRecordMapper.batchSave(saveRecords);
        }
    }

}
