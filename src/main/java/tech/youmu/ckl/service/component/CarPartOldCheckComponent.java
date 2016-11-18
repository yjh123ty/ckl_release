/**
 * Project Name:ckl
 * File Name:CarPartOldCheckComponent.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.AdminTask;
import tech.youmu.ckl.domain.CarPartStockIncome;
import tech.youmu.ckl.service.IAdminTaskService;
import tech.youmu.ckl.service.ICarPartStockIncomeService;
import tech.youmu.ckl.service.ICarPartStockService;
import tech.youmu.ckl.utils.ConfigUtil;

/**
 * <p>Title:CarPartOldCheckComponent</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月19日下午12:00:07</p>
 * <p>Description:零部件老化检查</p>
 */
@Component
public class CarPartOldCheckComponent {
    
    @Autowired
    private ICarPartStockService carPartStockService;
    
    @Autowired
    private ICarPartStockIncomeService carPartStockIncomeService;

    @Autowired
    private IAdminTaskService adminTaskServie;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    // 1.获取所有未出库的零部件
    /**
     * 关联入库出库记录查询出所有未出库的零部件
     * 入库记录数和出库记录数对应，则零部件已经出库完成，否则零部件出库未完成 
     * 1.查询零部件的入库记录数
     * 2.查询零部件的出库记录数
     * 比较得出零部件未出库的记录
     * 2.将未出库的记录查询零部件老化的零部件信息
     * 3.解析每个库管员的老化零部件数量
     */
    @Scheduled(cron="0 20 18 * * ?")
    public void checkCarPartOld(){
    	
        List<String> allCodes = Collections.emptyList();
        //　老化零部件编码
        Set<String> oldCodes = Collections.emptySet();
        // 老化零部件的库存信息(在哪个仓库)
        List<CarPartStockIncome> oldCarPartInfos = Collections.emptyList();
        //找出所有库管员
        Set<Long> keepers = new HashSet<>();
        
        allCodes = carPartStockService.findAllStockCarPartCode();
        Iterator<String> iterator = allCodes.iterator();
        while (iterator.hasNext()) {
            if(StringUtils.isBlank(iterator.next())){
                iterator.remove();
            }
        }
        
        //　查找所有的老化零部件编码
        if(allCodes.size() > 0){
        	oldCodes = carPartStockIncomeService.findOldCarPartCodes(allCodes, DateUtils.addDays(new Date(), ConfigUtil.getRemindOldDays()));
        }
        
        // 查找老化零部件的库存信息(在哪个仓库)
        if(oldCodes.size() > 0) {
        	oldCarPartInfos = carPartStockIncomeService.findOldCarPartInfos(oldCodes);
        }
        
        
        for (CarPartStockIncome carPartStockIncome : oldCarPartInfos) {
            keepers.add(carPartStockIncome.getDepot().getKeeper().getId());
        }
        
        //清空原有的零部件老化信息
	    carPartStockIncomeService.deleteCarPartOldInfo();
	    adminTaskServie.deleteCarPartOldTask();
	    logger.debug("零部件老化信息已经清空...");
	    
        // 找出库存过期的零部件数目
       List<AdminTask> adminTasks = new ArrayList<AdminTask>();
       for (Long id : keepers) {
           List<CarPartStockIncome> incomes = new ArrayList<CarPartStockIncome>();
           for (CarPartStockIncome carPartStockIncome : oldCarPartInfos) {
               if(id.equals(carPartStockIncome.getDepot().getKeeper().getId())){
                   incomes.add(carPartStockIncome);
               }
           }
           // 更新老化零部件信息到数据库
           if(incomes.size() > 0){
               carPartStockIncomeService.batchSaveCarPartOld(incomes);
               adminTasks.add(new AdminTask(id, AdminTask.CARPART_OLD_HANDLE_URL, "你有"+incomes.size()+"个老化的零部件需要处理", StatusConst.THREE));
           }
       }
       
       if(adminTasks.size() > 0){
           adminTaskServie.batchSave(adminTasks);
           logger.debug("零部件老化数量..."+adminTasks.size());
       }else{
    	   logger.debug("没有零部件老化...");
       }
    }
}
