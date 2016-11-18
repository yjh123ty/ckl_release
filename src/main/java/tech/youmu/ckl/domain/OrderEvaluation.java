/**
 * Project Name:ckl
 * File Name:OrderEvaluation.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * <p>Title:OrderEvaluation</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年8月26日下午5:46:33</p>
 * <p>Description:服务评价</p>
 */
public class OrderEvaluation {
    private Long id;
    private String name;        //员工姓名
    
    //数据字典中的服务评价
//    private DictionaryDetail detail;
    
    private String content;     //评价内容
    private Order order;        //订单
    private Integer statrs;     //评价星级（1-5星）
    private Boolean isDel;      //是否删除
    private Date createTime;    //创建时间
    private Date modifyTime;    //修改时间
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Integer getStatrs() {
        return statrs;
    }
    public void setStatrs(Integer statrs) {
        this.statrs = statrs;
    }
    public Boolean getIsDel() {
        return isDel;
    }
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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
    
    
    
}
