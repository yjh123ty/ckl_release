/**
 * Project Name:ckl
 * File Name:HotelRoomMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.HotelRoom;

/**
 * <p>Title:HotelRoomMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月9日下午3:15:08</p>
 * <p>Description:TODO</p>
 */
public interface HotelRoomMapper extends BaseMapper{

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午3:15:13;</p>
     *	<p>Description: 获取一个酒店下面的所有房间;</p>
     *  @return
     */
    List<HotelRoom> getHotelRooms(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午3:39:03;</p>
     *	<p>Description: 保存一个房间类型;</p>
     *  @param room
     *  @return
     */
    void save(HotelRoom room);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午3:39:17;</p>
     *	<p>Description: 添加一个房间类型;</p>
     *  @param room
     *  @return
     */
    void update(HotelRoom room);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午4:02:07;</p>
     *	<p>Description: 删除一个酒店房间;</p>
     *  @param id
     */
    void delete(Long id);

    void saveImgsUrls(@Param("list") List<String> urls, @Param("roomId") Long roomId);
    
    void deleteImgsUrlsByHotelRoomId(Long roomId);

    HotelRoom getHotelRoomById(Long hotelRoomId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午12:57:47;</p>
     *	<p>Description: 检查名称是否重复;</p>
     *  @param room
     *  @return
     */
    int getRepeatCount(HotelRoom room);

}
