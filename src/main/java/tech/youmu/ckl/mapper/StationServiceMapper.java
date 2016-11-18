/**
 * Project Name:ckl
 * File Name:StationServiceMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.domain.StationService;


/**
 * <p>Title:StationServiceMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月25日下午1:57:34</p>
 * <p>Description:站点服务的数据操作接口</p>
 */
public interface StationServiceMapper extends BaseMapper<StationService> {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午3:34:36;</p>
     *	<p>Description: 保存服务站 所有类型的空的服务 如果该类型存在则不保存;</p>
     *  @param services
     */
    void saveEmptyStationServices(List<StationService> services);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午3:34:36;</p>
     *  <p>Description: 删除一个站点不在该站类型里面的服务;</p>
     *  @param services
     */
    void deleteStationServicesNotInTypes(Station station);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午5:53:51;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    void deleteStationServicesByStationId(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日上午9:54:58;</p>
     *	<p>Description: 删除一个服务的服务内容 酒店;</p>
     *  @param id
     */
    void deleteServiceContents(Long serviceId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日上午9:56:48;</p>
     *	<p>Description: 保存一个服务的服务内容 酒店可用;</p>
     *  @param hotel
     */
    void saveServiceContents(StationService hotel);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日上午9:54:58;</p>
     *  <p>Description: 删除一个服务的服务详细</p>
     *  @param id
     */
    void deleteServiceDetails(Long serviceId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日上午9:56:48;</p>
     *  <p>Description: 保存一个服务的服务详细</p>
     *  @param service
     */
    void saveServiceDetails(StationService service);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月2日上午11:54:50;</p>
     *	<p>Description: 保存并获取id;</p>
     *  @param hotel
     */
    void saveAndGetId(StationService hotel);
}
