/**
 * Project Name:ckl
 * File Name:CarPartTypeServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.CarPartDepot;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CarPartDepotMapper;
import tech.youmu.ckl.query.CarPartDepotQuery;
import tech.youmu.ckl.service.ICarPartDepotService;

/**
 * <p>Title:CarPartTypeServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日下午2:52:59</p>
 * <p>Description:TODO</p>
 */
@Service
public class CarPartDepotServiceImpl extends BaseServiceImpl<CarPartDepot> implements ICarPartDepotService{
    
    private CarPartDepotMapper carPartDepotMapper;

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年9月20日下午2:53:03;</p>
     */
    @Autowired
    public CarPartDepotServiceImpl(CarPartDepotMapper carPartDepotMapper) {
        super(carPartDepotMapper);
        this.carPartDepotMapper = carPartDepotMapper;
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#save(java.lang.Object)
     */
    @Override
    public boolean save(CarPartDepot t) {
        if(carPartDepotMapper.checkCount(t)!=0){
            throw new BizExecption("仓库已经存在");
        }
        return super.save(t);
    }
    
    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.impl.BaseServiceImpl#updateById(java.lang.Object)
     */
    @Override
    public boolean updateById(CarPartDepot t) {
        if(carPartDepotMapper.checkCount(t)!=0){
            throw new BizExecption("仓库已经存在");
        }
        return super.updateById(t);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartDepotService#findCentreCarPartDepots()
     */
    @Override
    public List<CarPartDepot> findCentreCarPartDepots() {
        CarPartDepotQuery query = new CarPartDepotQuery();
        query.setCentre(true);
        return carPartDepotMapper.getByQuery(query);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartDepotService#findServiceStationCarPartDepots()
     */
    @Override
    public List<CarPartDepot> findServiceStationCarPartDepots() {
        CarPartDepotQuery query = new CarPartDepotQuery();
        query.setCentre(false);
        return carPartDepotMapper.getByQuery(query);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartDepotService#findCandidateKeepers()
     */
    @Override
    public List<Employee> findCandidateKeepers() {
        return carPartDepotMapper.findCandidateKeepers();
    }
}
