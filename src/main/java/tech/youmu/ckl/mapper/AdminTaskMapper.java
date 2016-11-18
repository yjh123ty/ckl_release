/**
 * Project Name:ckl
 * File Name:MessageCenterMapper.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.AdminTask;


/**
 * <p>Title:MessageCenterMapper</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月20日下午1:28:00</p>
 * <p>Description:TODO</p>
 */
public interface AdminTaskMapper {
       
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午5:55:18;</p>
     *	<p>Description: 添加一个任务;</p>
     *  @param task
     */
    void save(AdminTask task);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午5:54:41;</p>
     *	<p>Description: 员工处理该任务;</p>
     *  @param employeeId
     *  @param id
     */
    void handledTask(@Param("employeeId") Long employeeId, @Param("id")Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月21日下午5:56:08;</p>
     *	<p>Description: 查询当前用户的任务;</p>
     *  @param employeeId
     *  @return
     */
    List<AdminTask> getAdminAllTask(Long employeeId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日上午9:32:31;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @return
     */
    long getTaskCount(Long employeeId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日下午4:25:09;</p>
     *	<p>Description: 获取拥有该菜单的员工;</p>
     *  @param url
     *  @return
     */
    List<Long> getEmployeeIds(String url);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日下午5:47:54;</p>
     *	<p>Description: 批量添加后台消息;</p>
     *  @param adminTasks
     */
    void batchSave(@Param("adminTasks") List<AdminTask> adminTasks);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月25日下午4:27:46;</p>
     *	<p>Description: TODO;</p>
     */
    void deleteCarPartOldTask();

}
