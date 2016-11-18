/**
 * Project Name:ckl
 * File Name:IAddressService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.City;
import tech.youmu.ckl.domain.District;
import tech.youmu.ckl.domain.Province;

/**
 * <p>Title:IAddressService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:18:59</p>
 * <p>Description:省市区地址服务</p>
 */
public interface IAddressService {
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午1:16:02;</p>
     *  <p>Description: 获取所有的省;</p>
     *  @return
     */
    public List<Province> getAllProvince();
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午1:16:02;</p>
     *  <p>Description: 根据省获取市;</p>
     *  @return
     */
    public List<City> getCitysByProvinceCode(Integer code);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午1:17:40;</p>
     *  <p>Description: 根据市的编码获取下面所有的区;</p>
     *  @param code
     *  @return
     */
    public List<District> getDistrictsByCityCode(Integer code);

}
