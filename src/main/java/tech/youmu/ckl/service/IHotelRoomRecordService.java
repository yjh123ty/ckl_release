/**
 * Project Name:ckl
 * File Name:IHotelRoomRecordService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;
import java.util.Map;

import tech.youmu.ckl.domain.HotelRoomRecord;
import tech.youmu.ckl.query.HotelRoomRecordQuery;

/**
 * <p>Title:IHotelRoomRecordService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月12日上午10:34:27</p>
 * <p>Description:酒店房间记录的服务接口</p>
 */
public interface IHotelRoomRecordService {
    
    public void save(HotelRoomRecord hotelRoomRecord);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月12日上午10:35:08;</p>
     *	<p>Description: 获取酒店房间记录的分页对象;</p>
     *  @param query
     *  @return
     */
    Map<String, Object> getPageList(HotelRoomRecordQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日上午11:06:02;</p>
     *	<p>Description: 添加一个线下房间的记录数量;</p>
     *  @param recordId
     *  @return true 添加成功  false 不可添加  房间数量不足
     */
    boolean addOfflineRoomNum(Long recordId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日上午11:06:29;</p>
     *	<p>Description: 减少一个线下房间的记录数量;</p>
     *  @param recordId
     *  @return true 添加成功  false 不可添加  房间数量超过了最大限制
     */
    boolean removeOfflineRoomNum(Long recordId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月16日上午9:18:47;</p>
     *	<p>Description: 保存这一天的房间记录   如果该条记录不存在;</p>
     *  @param record
     */
    public void saveIfNotExist(HotelRoomRecord record);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月16日上午9:45:35;</p>
     *	<p>Description: 保存多个不存在;</p>
     *  @param hotelRoomRecords
     */
    public void saveIfNotExist(List<HotelRoomRecord> hotelRoomRecords);

}
