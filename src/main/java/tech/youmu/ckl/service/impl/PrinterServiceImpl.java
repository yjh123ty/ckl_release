/**
 * Project Name:ckl
 * File Name:PrinterServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Printer;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.PrinterMapper;
import tech.youmu.ckl.service.IPrinterService;
import tech.youmu.ckl.utils.PrintMessageUtil;

/**
 * <p>Title:PrinterServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年11月3日下午1:06:12</p>
 * <p>Description:云打印机服务层</p>
 */
@Service
public class PrinterServiceImpl extends BaseServiceImpl<Printer> implements IPrinterService{

    
    private PrinterMapper printerMapper;
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年11月3日下午1:08:49;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public PrinterServiceImpl(PrinterMapper printerMapper) {
        super(printerMapper);
        this.printerMapper = printerMapper;
    }
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IPrinterService#getByStationId(java.lang.Long)
     */
    @Override
    public List<Printer> getByStationId(Long stationId) {
        if(stationId== null){
            throw new BizExecption("服务站为空！");
        }
        return printerMapper.getByStationId(stationId);
    }
    
    
}
