/**
 * @Title: Feedback.java
 * @Package tech.youmu.ckl.domain
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016�?�?8�?上午9:58:22
 * @version V1.0
 */

package tech.youmu.ckl.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户反馈
 * @author youmu-yjh
 * 
 */
public class Feedback {
    
    /**
     * 已经处理
     */
    public static final int STATUS_COMPLETE_FEEDBACK = 1; 
    
    /**
     * 等待处理
     */
    public static final int STATUS_WAITING_FEEDBACK = 0;   
    
    private Long id;
    private String content;     //反馈内容
    
    private Long userId;
    /**
     * @see Feedback#STATUS_COMPLETE_FEEDBACK
     * @see Feedback#STATUS_WAITING_FEEDBACK
     */
    private Integer status;     //处理状态
    private User user;          //反馈用户（账号和昵称）
    private Employee employee;  //处理人
    private Date handleTime;    //处理日期
    private Date createTime;    //提交日期
    private Date modifyTime;    //修改日期
    private Boolean isDel;      //是否删除
    
    public Feedback(){}
    
    public Feedback(Long userId, String content) {
           this.userId = userId;
           this.content = content;
                
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
    
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "GMT+8")
    public Date getHandleTime() {
        return handleTime;
    }
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
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
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Feedback [id=" + id + ", content=" + content + ", userId=" + userId + ", status=" + status + ", user=" + user + ", employee=" + employee + ", handleTime=" + handleTime + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }
    
    
}
