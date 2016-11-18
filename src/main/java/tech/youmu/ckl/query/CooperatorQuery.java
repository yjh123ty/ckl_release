/**
 * Project Name:ckl
 * File Name:CooperatorQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:CooperatorQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月5日下午2:05:58</p>
 * <p>Description:TODO</p>
 */
public class CooperatorQuery extends BaseQuery {
    private String keywords;    //关键词（酒店名称）
    
    private Integer type;       //商家类型

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    
}
