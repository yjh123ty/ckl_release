/**
 * Project Name:ckl
 * File Name:HotelMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.HotelDetailInfo;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.query.HotelQuery;

/**
 * <p>Title:HotelMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午4:28:38</p>
 * <p>Description:服务站酒店数据访问接口</p>
 */
public interface HotelMapper extends BaseMapper<Hotel> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午4:25:34;</p>
     *	<p>Description: 保存并回填id;</p>
     *  @param hotel
     */
    void saveAndGetId(Hotel hotel);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午4:28:33;</p>
     *	<p>Description: 保存酒店的服务内容;</p>
     *  @param hotel
     */
    void saveHotelContents(Hotel hotel);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月8日下午5:12:45;</p>
     *	<p>Description: 删除该酒店的所有服务内容;</p>
     *  @param id
     */
    void deleteHotelContents(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午4:51:29;</p>
     *	<p>Description: 酒店外观图保存;</p>
     *  @param outsideImgUrls
     *  @param id
     */
    void saveOutSideImgUrls(@Param("list") List<String> outsideImgUrls, @Param("hotelId") Long hotelId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午4:51:29;</p>
     *	<p>Description: 酒店外观图保存;</p>
     *  @param outsideImgUrls
     *  @param id
     */
    void deleteOutSideImgUrls(Long hotelId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午4:51:29;</p>
     *	<p>Description: 保存酒店内部图片;</p>
     *  @param outsideImgUrls
     *  @param id
     */
    void saveInnerImgUrls(@Param("list") List<String> innerImgUrls, @Param("hotelId") Long hotelId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月18日下午4:59:47;</p>
     *	<p>Description: 删除酒店内部图片;</p>
     *  @param hotelId
     */
    void deleteInnerImgUrls(Long hotelId);
    
    int getRepeatCount(Hotel hotel);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月18日下午4:15:48;</p>
     *	<p>Description: TODO;</p>
     *  @param hotelId
     * @return 
     */
    Hotel getHotelById(Long hotelId);
    
    void changeStatus(Hotel hotel);

    List<Hotel> findHotel(@Param("startTime")String startTime,@Param("sort")Integer sort,@Param("stars")Integer stars, @Param("startPrice")Integer startPrice, @Param("endPrice")Integer endPrice,@Param("search")String search,@Param("stationId")Long stationId);

    HotelDetailInfo getHotelDetailInfo(@Param("hotelId")Long hotelId,@Param("startTime")String startTime);

}
