/**
 * Project Name:ckl
 * File Name:ServiceEvaluationDetail.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.domain;

import java.util.Date;

/**
 * <p>
 *  Title:ServiceEvaluationDetail
 * </p>
 * @author yjh
 * @version v1.0
 *          <p>Date:2016年8月30日下午5:37:23</p>
 *          <p>Description:服务评价明细,与订单的关系：多对一</p>
 */
public class ServiceEvaluationDetail {
    private Long id;

    private String name; //服务项的名称(例：服务速度，服务态度等)

    private Order order; //对应的服务订单

    private Integer star; //服务获得星数（1-5星）

    private Date createTime; //创建时间

    private Date modifyTime; //修改时间

    private Boolean isDel; //是否删除

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
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

    @Override
    public String toString() {
        return "ServiceEvaluationDetail [id=" + id + ", name=" + name + ", order=" + order + ", star=" + star + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", isDel=" + isDel + "]";
    }

}
