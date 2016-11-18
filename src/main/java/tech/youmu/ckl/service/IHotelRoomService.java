/**
 * Project Name:ckl
 * File Name:IHotelRoomService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.HotelRoom;

/**
 * <p>Title:IHotelRoomService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月9日下午3:12:04</p>
 * <p>Description:酒店房间服务</p>
 */
public interface IHotelRoomService {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午3:13:05;</p>
     *	<p>Description:   获取该酒店下面的所有房间类型;</p>
     *  @param hotelId
     *  @return
     */
    public List<HotelRoom> getHotelRooms(Long hotelId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午3:38:06;</p>
     *	<p>Description: 保存一个房间类型;</p>
     * @param coverImg 封面图片
     * @param images 
     *  @param room
     */
    public void save(MultipartFile coverImg, MultipartFile[] images, HotelRoom room);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午3:38:22;</p>
     *	<p>Description: 修改一个客房类型;</p>
     * @param coverImg 封面图片
     * @param images 
     *  @param room
     */
    public void update(MultipartFile coverImg, MultipartFile[] images, HotelRoom room);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月9日下午4:01:38;</p>
     *	<p>Description: 删除一个;</p>
     *  @param id
     */
    public void delete(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月9日下午6:09:28;</p>
     *	<p>Description: 查询所有的房间信息;</p>
     *  @return
     */
    public List<HotelRoom> getAll();
}
