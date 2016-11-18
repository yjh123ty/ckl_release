/**
 * Project Name:ckl
 * File Name:CarPartStockQuery.java
 * Copyright (c) 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:CarPartStockQuery</p>
 * @author yjh
 * @version	v1.0
 * <p>Date:2016年9月21日下午12:07:04</p>
 * <p>Description:入库出库记录查询对象</p>
 */
public class CarPartStockInfoQuery extends BaseQuery {
    private String keywords;
    
    private Integer stockType;
    private String beginInStockTimeStr;
    private String endInStockTimeStr;
    private String beginOutStockTimeStr ;
    private String endOutStockTimeStr ;
    private String beginInStationStockTimeStr ;
    private String endInStationStockTimeStr ;
    
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public Integer getStockType() {
        return stockType;
    }
    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }
    public String getBeginInStockTimeStr() {
        return beginInStockTimeStr;
    }
    public void setBeginInStockTimeStr(String beginInStockTimeStr) {
        this.beginInStockTimeStr = beginInStockTimeStr;
    }
    public String getEndInStockTimeStr() {
        return endInStockTimeStr;
    }
    public void setEndInStockTimeStr(String endInStockTimeStr) {
        this.endInStockTimeStr = endInStockTimeStr;
    }
    public String getBeginOutStockTimeStr() {
        return beginOutStockTimeStr;
    }
    public void setBeginOutStockTimeStr(String beginOutStockTimeStr) {
        this.beginOutStockTimeStr = beginOutStockTimeStr;
    }
    public String getEndOutStockTimeStr() {
        return endOutStockTimeStr;
    }
    public void setEndOutStockTimeStr(String endOutStockTimeStr) {
        this.endOutStockTimeStr = endOutStockTimeStr;
    }
    public String getBeginInStationStockTimeStr() {
        return beginInStationStockTimeStr;
    }
    public void setBeginInStationStockTimeStr(String beginInStationStockTimeStr) {
        this.beginInStationStockTimeStr = beginInStationStockTimeStr;
    }
    public String getEndInStationStockTimeStr() {
        return endInStationStockTimeStr;
    }
    public void setEndInStationStockTimeStr(String endInStationStockTimeStr) {
        this.endInStationStockTimeStr = endInStationStockTimeStr;
    }
    
    
}
