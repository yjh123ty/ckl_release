/**
 * Project Name:ckl
 * File Name:CarPartStockServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.CarPartStock;
import tech.youmu.ckl.mapper.CarPartStockMapper;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartStockService;

/**
 * <p>Title:CarPartStockServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月14日上午10:21:52</p>
 * <p>Description:TODO</p>
 */
@Service
public class CarPartStockServiceImpl  implements ICarPartStockService{
    
    @Autowired
    private  CarPartStockMapper carPartStockMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockService#getPageList(tech.youmu.ckl.query.BaseQuery)
     */
    @Override
    public PageList<CarPartStock> getPageList(BaseQuery query) {
        PageList<CarPartStock> pageList = new PageList<CarPartStock>();
        long count = carPartStockMapper.getCountByQuery(query);
        if(count > 0) {
            pageList.setTotal(count);
            pageList.setRows(carPartStockMapper.getByQuery(query));
        }
        return pageList;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockService#findAllStockCarPartCode()
     */
    @Override
    public List<String> findAllStockCarPartCode() {
        return carPartStockMapper.findAllStockCarPartCode();
    }
}
