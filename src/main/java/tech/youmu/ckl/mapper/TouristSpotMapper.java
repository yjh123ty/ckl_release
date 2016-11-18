/**
 * Project Name:ckl
 * File Name:TouristSpot.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.Station;

/**
 * <p>Title:TouristSpot</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月22日下午12:04:23</p>
 * <p>Description:景点的mapper</p>
 */
public interface TouristSpotMapper extends BaseMapper<Station> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午5:02:18;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     *  @param urls
     */
    void saveImgsUrls(@Param("stationId") Long id, @Param("list") List<String> urls);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午5:02:51;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    void deleteImgsUrls(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月14日下午5:29:35;</p>
     *	<p>Description: 检查添加的景点是否重复;</p>
     *  @param station
     *  @return
     */
    int getRepeatCount(Station station);
    
       
}
