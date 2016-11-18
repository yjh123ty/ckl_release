/**
 * Project Name:ckl
 * File Name:AddressServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.City;
import tech.youmu.ckl.domain.District;
import tech.youmu.ckl.domain.Province;
import tech.youmu.ckl.mapper.AddressMapper;
import tech.youmu.ckl.service.IAddressService;

/**
 * <p>Title:AddressServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:19:47</p>
 * <p>Description:TODO</p>
 */
@Service
public class AddressServiceImpl implements IAddressService {
    
    @Autowired
    private AddressMapper addressMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAddressService#getAllProvince()
     */
    @Override
    public List<Province> getAllProvince() {
        return addressMapper.getAllProvince();
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAddressService#getCitysByProvinceCode(java.lang.Integer)
     */
    @Override
    public List<City> getCitysByProvinceCode(Integer code) {
        return addressMapper.getCitysByProvinceCode(code);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IAddressService#getDistrictsByCityCode(java.lang.Integer)
     */
    @Override
    public List<District> getDistrictsByCityCode(Integer code) {
        // TODO Auto-generated method stub
        return addressMapper.getDistrictsByCityCode(code);
    }
}
