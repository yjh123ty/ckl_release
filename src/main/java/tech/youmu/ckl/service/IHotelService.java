/**
 * Project Name:ckl
 * File Name:IHotelService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.HotelDetailInfo;
import tech.youmu.ckl.app.vo.HotelInfo;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.SysDicDetail;

/**
 * <p>Title:IHotelService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午4:26:31</p>
 * <p>Description:酒店服务接口</p>
 */
public interface IHotelService extends IBaseService<Hotel> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日上午11:23:52;</p>
     *	<p>Description: 查询酒店的服务内容;</p>
     *  @return
     */
    List<SysDicDetail> getServiceContents();

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午4:24:29;</p>
     *	<p>Description: 保存一个酒店和酒店的服务内容 返回酒店的id;</p>
     * @param coverImg 封面图片
     * @param innerImgs  大厅图片
     * @param outsideImgs  外观图片
     * @return 
     */
    Long saveHotelAndServiceContents(MultipartFile coverImg, MultipartFile[] outsideImgs, MultipartFile[] innerImgs, Hotel hotel);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午5:11:35;</p>
     *	<p>Description: 修改一个酒店和酒店的服务内容;</p>
     * @param coverImg 封面图片
     * @param innerImgs 大厅图片
     * @param outsideImgs  外观图片
     *  @param hotel
     */
    void updateHotelAndServiceContents(MultipartFile coverImg, MultipartFile[] outsideImgs, MultipartFile[] innerImgs, Hotel hotel);

    
    void changeStatus(Hotel hotel);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月19日下午5:35:47;</p>
     *	<p>Description: 酒店信息;</p>
     *  @param startTime
     *  @param endTime
     *  @param lng
     *  @param lat
     *  @param sort
     *  @param stars
     *  @param starPrice
     *  @param endPrice
     *  @param search
     *  @return
     */
    List<HotelInfo> findHotelInfo(String startTime, String lng, String lat, Integer sort, Integer stars, Integer startPrice, Integer endPrice, String search,Long stationId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月20日下午2:25:35;</p>
     *	<p>Description:酒店明细;</p>
     *  @param hotelId
     *  @return
     */
    HotelDetailInfo getHotelDetailInfo(Long hotelId, String startTime);
    
    void saveCooperator(MultipartFile coverImg, MultipartFile[] outsideImgs, Hotel hotel);
    
    void updateCooperator(MultipartFile coverImg, MultipartFile[] outsideImgs, Hotel hotel);
}
