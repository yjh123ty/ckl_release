/**
 * Project Name:ckl
 * File Name:IMessageCenterService.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.AdminTask;


/**
 * <p>Title:IMessageCenterService</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月20日下午1:39:20</p>
 * <p>Description:管理员任务服务层</p>
 */
public interface IAdminTaskService {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午5:55:18;</p>
     *  <p>Description: 添加一个任务;</p>
     *  @param task
     */
    void save(AdminTask task);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午5:54:41;</p>
     *  <p>Description: 员工处理该任务;</p>
     *  @param employeeId
     *  @param id
     */
    void handledTask(@Param("employeeId") Long employeeId, @Param("id")Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午5:56:08;</p>
     *  <p>Description: 查询当前用户的任务;</p>
     *  @param employeeId
     *  @return
     */
    List<AdminTask> getAdminAllTask(Long employeeId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日上午9:31:36;</p>
     *	<p>Description: 查询任务的条数;</p>
     *  @param employeeId
     */
    long getTaskCount(Long employeeId);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日下午4:31:37;</p>
     *	<p>Description: 向后台管理界面发送消息;</p>
     *  @param adminTask
     */
    void push(AdminTask adminTask);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日下午5:46:51;</p>
     *	<p>Description: 批量添加后台消息;</p>
     *  @param adminTasks
     */
    void batchSave(List<AdminTask> adminTasks);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午4:27:13;</p>
     *	<p>Description: 删除老化商品;</p>
     */
    void deleteCarPartOldTask();
}
