/**
 * Project Name:ckl
 * File Name:ProductQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title:ProductQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月20日上午11:29:41</p>
 * <p>Description:商品查询对象</p>
 */
public class CarPartQuery extends BaseQuery {
    
    private String name;
    
    private String sn;
    
    private String keywords;
    
    private Long typeId;
    
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getKeywords() {
        if(StringUtils.isBlank(keywords)) {
            return null;
        }
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
