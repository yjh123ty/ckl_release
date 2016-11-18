/**
 * Project Name:ckl
 * File Name:ITouristSpotService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.Station;

/**
 * <p>Title:ITouristSpotService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月22日下午2:29:09</p>
 * <p>Description:景点的服务接口</p>
 */
public interface ITouristSpotService extends IBaseService<Station> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午4:52:34;</p>
     *	<p>Description: 保存景点;</p>
     *  @param images
     *  @param station
     */
    void save(MultipartFile[] images, Station station);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月22日下午4:52:43;</p>
     *	<p>Description: 修改景点;</p>
     *  @param images
     *  @param station
     */
    void update(MultipartFile[] images, Station station);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月14日下午5:28:07;</p>
     *	<p>Description: 检查景点是否重复;</p>
     *  @param station
     *  @return
     */
    boolean checkRepeat(Station station);
    
}
