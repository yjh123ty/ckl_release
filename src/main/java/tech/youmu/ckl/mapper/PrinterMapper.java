package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.Printer;

public interface PrinterMapper extends BaseMapper<Printer>{

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年11月3日下午3:55:46;</p>
     *	<p>Description: 根据订单上的服务站id获取该服务站绑定的打印机信息;</p>
     *  @param stationId
     *  @return
     */
    List<Printer> getByStationId(Long stationId);
    
}