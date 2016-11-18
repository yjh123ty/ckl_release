/**
 * Project Name:ckl
 * File Name:AddressController.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tech.youmu.ckl.domain.City;
import tech.youmu.ckl.domain.District;
import tech.youmu.ckl.domain.Province;
import tech.youmu.ckl.service.IAddressService;

/**
 * <p>Title:AddressController</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月1日下午1:22:04</p>
 * <p>Description:省市区地址控制器</p>
 */
@Controller
@RequestMapping("/address")
public class AddressController {
    
      @Autowired  
      private IAddressService addressService;
      
      @RequestMapping("/getProvinces")
      @ResponseBody
      public List<Province> getAllProvice(){
          return addressService.getAllProvince();
      }
      
      @RequestMapping("/getCitys")
      @ResponseBody
      public List<City> getCitys(Integer code){
          return  code == null ? new ArrayList<City>(0) : addressService.getCitysByProvinceCode(code);
      }
      
      @RequestMapping("/getDistricts")
      @ResponseBody
      public List<District> getDistricts(Integer code){
          return  code == null ? new ArrayList<District>(0) : addressService.getDistrictsByCityCode(code);
      }
}
