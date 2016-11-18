/**
 * Project Name:ckl
 * File Name:AdminMessage.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * <p>Title:AdminMessage</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月21日下午4:45:31</p>
 * <p>Description:后台任务</p>
 */
public class AdminTask {
    
    public static final String SOS_HANDLE_URL = "/sos/index.do";
    
    public static final String REFUND_HANDLE_URL = "/order/index.do?cmd=refund";
    
    public static final String CARPART_OLD_HANDLE_URL = "/carPartStockIncome/index.do?cmd=oldList";
    
    private Long id;
    
    /**
     * url 权限 拥有url权限的消息(公有任务)
     */
    private String url;
    
    /**
     * 单个用户的任务(私有任务)
     */
    private Long employeeId;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * 类型 ： 1.sos 2.退款  3.库存商品老化
     */
    private Integer type;
    
    /**
     * 该任务是否处理
     */
    private Boolean isHandle;
    
    /**
     * 处理的员工
     */
    private Long handleEmployeeId;
    
    /**
     * 处理的时间
     */
    private Date handleTime;
    
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;
    
    /**
     * 是否删除
     */
    private Boolean isDel;

    public AdminTask() {
    }
    public AdminTask(String url, String message, Integer type) {
        this.url = url;
        this.content = message;
        this.type = type;
    }

    public AdminTask(Long employeeId, String url, String content, int type) {
        this.url = url;
        this.employeeId = employeeId;
        this.content = content;
        this.type = type;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Boolean isHandle) {
        this.isHandle = isHandle;
    }

    public Long getHandleEmployeeId() {
        return handleEmployeeId;
    }

    public void setHandleEmployeeId(Long handleEmployeeId) {
        this.handleEmployeeId = handleEmployeeId;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}
