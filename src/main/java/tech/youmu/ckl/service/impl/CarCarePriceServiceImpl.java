/**
 * Project Name:ckl
 * File Name:CarCarePriceServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.CarCarePriceInfo;
import tech.youmu.ckl.domain.CarCarePrice;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.CarCarePriceMapper;
import tech.youmu.ckl.service.ICarCarePriceService;
import tech.youmu.ckl.utils.UploadUtils;


/**
 * <p>Title:CarCarePriceServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月10日上午10:36:09</p>
 * <p>Description:TODO</p>
 */
@Service
public class CarCarePriceServiceImpl extends BaseServiceImpl<CarCarePrice> implements ICarCarePriceService{

    private CarCarePriceMapper carCarePriceMapper;

    @Autowired
    public CarCarePriceServiceImpl(CarCarePriceMapper carCarePriceMapper) {
        super(carCarePriceMapper);
        this.carCarePriceMapper = carCarePriceMapper;
    }

    @Override
    public List<CarCarePriceInfo> findCarCarePriceInfo(String key) {
        return carCarePriceMapper.findCarCarePriceInfo(key);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarCarePriceService#save(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.CarCarePrice)
     */
    @Override
    public void save(MultipartFile img, CarCarePrice carCarePrice) {
        if(carCarePriceMapper.getRepeatCount(carCarePrice) > 0) {
            throw new BizExecption("汽车维护项目名称重复");
        }
        if(img != null && !img.isEmpty()) {
            carCarePrice.setPic(UploadUtils.uploadThumbnail(img, UploadUtils.CAR_CARE_ITEM, 130, 130));
        }
        super.save(carCarePrice);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ICarCarePriceService#update(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.CarCarePrice)
     */
    @Override
    public void update(MultipartFile img, CarCarePrice carCarePrice) {
        if(carCarePriceMapper.getRepeatCount(carCarePrice) > 0) {
            throw new BizExecption("汽车维护项目名称重复");
        }
        if(img != null && !img.isEmpty()) {
            carCarePrice.setPic(UploadUtils.uploadThumbnail(img, UploadUtils.CAR_CARE_ITEM, 130, 130));
        }
        super.updateById(carCarePrice);
    }

}
