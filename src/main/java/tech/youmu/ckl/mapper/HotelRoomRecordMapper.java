/**
 * Project Name:ckl
 * File Name:HotelRoomRecordMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.HotelRoomRecord;
import tech.youmu.ckl.domain.HotelRoomRecordInfo;
import tech.youmu.ckl.query.HotelRoomRecordQuery;

/**
 * <p>Title:HotelRoomRecordMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月12日上午10:38:21</p>
 * <p>Description:酒店房间记录的数据访问接口</p>
 */
public interface HotelRoomRecordMapper {
    
    public void save(HotelRoomRecord record);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月12日上午10:38:38;</p>
     *	<p>Description: 获取酒店房间记录的数量;</p>
     *  @param query
     *  @return
     */
    long getCountByQuery(HotelRoomRecordQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月12日上午10:39:09;</p>
     *	<p>Description: 根据条件查询酒店房间记录;</p>
     *  @param query
     *  @return
     */
    List<HotelRoomRecordInfo> getByQuery(HotelRoomRecordQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日上午11:07:48;</p>
     *	<p>Description: 添加一个线下房间数量;</p>
     *  @param recordId
     */
    public int addOfflineRoomNum(Long recordId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月13日上午11:08:19;</p>
     *	<p>Description: 线下房间数量减一;</p>
     *  @param recordId
     */
    public int removeOfflineRoomNum(Long recordId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月16日上午9:25:09;</p>
     *	<p>Description: 查询这一天的房间记录数目;</p>
     *  @param id
     *  @param bookDate
     *  @return
     */
    public long existRecordCount(@Param("id") Long id, @Param("bookDate") Date bookDate);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年11月16日上午9:49:42;</p>
     *	<p>Description: 批量添加房间记录;</p>
     *  @param saveRecords
     */
    public void batchSave(@Param("records") List<HotelRoomRecord> saveRecords);

}
