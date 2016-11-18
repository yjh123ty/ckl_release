/**
 * Project Name:ckl
 * File Name:CarPartDepotQuery.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.query;

/**
 * <p>Title:CarPartDepotQuery</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月13日下午5:07:07</p>
 * <p>Description:零部件仓库查询对象</p>
 */
public class CarPartDepotQuery extends BaseQuery {
    
    /**
     * 是否是中央仓库
     */
    private Boolean centre;

    public Boolean getCentre() {
        return centre;
    }

    public void setCentre(Boolean centre) {
        this.centre = centre;
    }
    
}
