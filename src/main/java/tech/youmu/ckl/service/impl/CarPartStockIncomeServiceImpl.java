/**
 * Project Name:ckl
 * File Name:CarPartStockIncomeService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.CarPartDepot;
import tech.youmu.ckl.domain.CarPartStock;
import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CarPartDepotMapper;
import tech.youmu.ckl.mapper.CarPartStockIncomeMapper;
import tech.youmu.ckl.mapper.CarPartStockMapper;
import tech.youmu.ckl.query.CarPartStockIncomeQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.ICarPartStockIncomeService;
import tech.youmu.ckl.utils.UserContext;

/**
 * <p>Title:CarPartStockIncomeService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月13日下午2:28:59</p>
 * <p>Description:零部件入库服务</p>
 */
@Service
public class CarPartStockIncomeServiceImpl extends BaseServiceImpl<CarPartStockIncome> implements ICarPartStockIncomeService {

    private CarPartStockIncomeMapper carPartStockIncomeMapper;
    
    @Autowired
    private CarPartDepotMapper carPartDepotMapper;
    
    @Autowired
    private  CarPartStockMapper carPartStockMapper;

    @Autowired
    public CarPartStockIncomeServiceImpl(CarPartStockIncomeMapper carPartStockIncomeMapper) {
        super(carPartStockIncomeMapper);
        this.carPartStockIncomeMapper = carPartStockIncomeMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockIncomeService#findOldCarPartInfos(java.util.List)
     */
    @Override
    public List<CarPartStockIncome> findOldCarPartInfos(Set<String> carPartCodes) {
        return carPartStockIncomeMapper.findOldCarPartInfos(carPartCodes);
    }

    @Override
    public void income(Long employeeId, Integer incomeType, List<CarPartStockIncome> carPartStockIncomes) {
        CarPartDepot carPartDepot =carPartDepotMapper.getByEmployeeId(employeeId);
        if(carPartDepot == null){
            throw new BizExecption("没有所属仓库不能入库");
        }
        List<CarPartStockIncome> dbCarPartStockIncomes = carPartStockIncomeMapper.findRlreadyIncome(carPartStockIncomes,incomeType);
        if(dbCarPartStockIncomes!=null&&dbCarPartStockIncomes.size()>0){
            StringBuffer buffer = new StringBuffer();
            for(CarPartStockIncome carPartStockIncome:dbCarPartStockIncomes){
                buffer.append(carPartStockIncome.getCode()).append(";");
            }
            throw new BizExecption("已入库的条形码："+buffer.toString());
        }
        carPartStockIncomeMapper.batchSaveCarPartStockIncome(employeeId,incomeType,carPartDepot.getId(),carPartStockIncomes);
        Map<Long,Integer> map = new HashMap();
        for(CarPartStockIncome carPartStockIncome:carPartStockIncomes){
            Long carPartId = carPartStockIncome.getCarPartId();
            Integer number = map.get(carPartStockIncome.getCarPartId());
            if(number==null||number==0){
                map.put(carPartId, 1);
            }else{
                map.put(carPartId, number+1);
            }
        }
        List<Long> carPartIds=new ArrayList(map.keySet());
        List<CarPartStock> carPartStocks = carPartStockMapper.findByDepotIdAndCarPartId(carPartDepot.getId(),carPartIds);
        List<CarPartStock> addCarPartStocks = new ArrayList<>();
        List<CarPartStock> updateCarPartStocks = new ArrayList<>();
        List<Long> dbCarPartIds=new ArrayList();
        for(CarPartStock carPartStock :carPartStocks){
            Long carPartId = carPartStock.getCarPartId();
            int num = carPartStock.getNum()+map.get(carPartId);
            carPartStock.setNum(num);
            updateCarPartStocks.add(carPartStock);
            dbCarPartIds.add(carPartId);
        }
        for(Long carPartId:carPartIds){
            if(!dbCarPartIds.contains(carPartId)){
                CarPartStock carPartStock = new CarPartStock(carPartId,carPartDepot.getId(),map.get(carPartId));
                addCarPartStocks.add(carPartStock);
            }
        }
        if(addCarPartStocks.size()>0){
            carPartStockMapper.batchSaveCarPartStock(addCarPartStocks);
        }
        if(updateCarPartStocks.size()>0){
            carPartStockMapper.batchUpdateCarPartStockNum(updateCarPartStocks);
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockIncomeService#getOldCarPartPageList(tech.youmu.ckl.query.CarPartStockIncomeQuery)
     */
    @Override
    public PageList<CarPartStockIncome> getOldCarPartPageList(CarPartStockIncomeQuery query) {
        query.setKeeperId(UserContext.getUser().getId());
        PageList<CarPartStockIncome> pageList = PageList.emptyPageList();
        long count = carPartStockIncomeMapper.getOldCarPartCountByQuery(query);
        if(count > 0) {
            pageList.setTotal(count);
            pageList.setRows(carPartStockIncomeMapper.getOldCarPartByQuery(query));
        }
        return pageList;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockIncomeService#findOldCarPartCodes(java.util.List, java.util.Date)
     */
    @Override
    public Set<String> findOldCarPartCodes(List<String> allCodes, Date days) {
        return carPartStockIncomeMapper.findOldCarPartCodes(allCodes, days);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockIncomeService#deleteCarPartOldInfo()
     */
    @Override
    public void deleteCarPartOldInfo() {
        carPartStockIncomeMapper.deleteCarPartOldInfo();
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarPartStockIncomeService#batchSaveCarPartOld(tech.youmu.ckl.domain.CarPartStockIncome)
     */
    @Override
    public void batchSaveCarPartOld(List<CarPartStockIncome> incomes) {
        carPartStockIncomeMapper.batchSaveCarPartOld(incomes);
    }

}
