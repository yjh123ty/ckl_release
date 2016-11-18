/**
 * Project Name:ckl
 * File Name:MessageCenterServiceImpl.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import com.google.gson.GsonBuilder;

import tech.youmu.ckl.domain.AdminTask;
import tech.youmu.ckl.mapper.AdminTaskMapper;
import tech.youmu.ckl.service.IAdminTaskService;
import tech.youmu.ckl.web.websocket.AdminTaskWebSocketHandler;

/**
 * <p>Title:MessageCenterServiceImpl</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年10月20日下午1:40:32</p>
 * <p>Description:TODO</p>
 */
@Service
public class AdminTaskServiceImpl implements IAdminTaskService{
    
    @Autowired
    private AdminTaskMapper adminTaskMapper;
    
    @Autowired
    private AdminTaskWebSocketHandler handler;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#save(tech.youmu.ckl.domain.AdminTask)
     */
    @Override
    public void save(AdminTask task) {
        adminTaskMapper.save(task);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#handledTask(java.lang.Long, java.lang.Long)
     */
    @Override
    public void handledTask(Long employeeId, Long id) {
        adminTaskMapper.handledTask(employeeId, id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#getAdminAllTask(java.lang.Long)
     */
    @Override
    public List<AdminTask> getAdminAllTask(Long employeeId) {
        return adminTaskMapper.getAdminAllTask(employeeId);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#getTaskCount(java.lang.Long)
     */
    @Override
    public long getTaskCount(Long employeeId) {
        return adminTaskMapper.getTaskCount(employeeId);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#push(tech.youmu.ckl.domain.AdminTask)
     */
    @Override
    public void push(AdminTask adminTask) {
        String url = adminTask.getUrl();
        List<Long> ids = null;
        String message = new GsonBuilder().create().toJson(adminTask);
        if(StringUtils.isNotBlank(url)){
            ids = adminTaskMapper.getEmployeeIds(url);
        }else {
            handler.sendMessage(adminTask.getEmployeeId(), new TextMessage(message));
            return;
        }
        if(ids != null && ids.size() != 0) {
            handler.sendMessage(ids, new TextMessage(message));
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#batchSave(java.util.List)
     */
    @Override
    public void batchSave(List<AdminTask> adminTasks) {
        adminTaskMapper.batchSave(adminTasks);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAdminTaskService#deleteCarPartOldTask()
     */
    @Override
    public void deleteCarPartOldTask() {
        adminTaskMapper.deleteCarPartOldTask();
    }
}
