/**
 * Project Name:ckl
 * File Name:IPrinterService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.Printer;

/**
 * <p>Title:IPrinterService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年11月3日下午1:05:18</p>
 * <p>Description:云打印机服务层接口</p>
 */
public interface IPrinterService extends IBaseService<Printer>{
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年11月3日下午3:53:41;</p>
     *	<p>Description: 根据订单上的服务站id获取该服务站绑定的打印机信息;</p>
     *  @param stationId 订单上的服务站Id
     *  @return
     */
    List<Printer> getByStationId(Long stationId);
}
