/**
 * Project Name:ckl
 * File Name:AddressMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.City;
import tech.youmu.ckl.domain.District;
import tech.youmu.ckl.domain.Province;

/**
 * <p>Title:AddressMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:01:31</p>
 * <p>Description:省市区地址数据访问接口</p>
 */
public interface AddressMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月1日下午1:16:02;</p>
     *	<p>Description: 获取所有的省;</p>
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
     *	<p>Description: 根据市的编码获取下面所有的区;</p>
     *  @param code
     *  @return
     */
    public List<District> getDistrictsByCityCode(Integer code);

}
