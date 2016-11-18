/**
 * Project Name:ckl
 * File Name:HotelServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.HotelDetailInfo;
import tech.youmu.ckl.app.vo.HotelInfo;
import tech.youmu.ckl.app.vo.HotelRoomInfo;
import tech.youmu.ckl.constants.SysDicType;
import tech.youmu.ckl.domain.Hotel;
import tech.youmu.ckl.domain.SysDicDetail;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.HotelMapper;
import tech.youmu.ckl.mapper.SysDicMapper;
import tech.youmu.ckl.service.IHotelService;
import tech.youmu.ckl.utils.GaodeUtil;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * <p>Title:HotelServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月7日下午4:27:25</p>
 * <p>Description:酒店服务实现</p>
 */
@Service
public class HotelServiceImpl extends BaseServiceImpl<Hotel> implements IHotelService {

    private HotelMapper hotelMapper;
    
    @Autowired
    private SysDicMapper sysDicMapper;

    @Autowired
    public HotelServiceImpl(HotelMapper hotelMapper) {
        super(hotelMapper);
        this.hotelMapper = hotelMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelService#getServiceContents()
     */
    @Override
    public List<SysDicDetail> getServiceContents() {
        return sysDicMapper.getDetailsByTypeName(SysDicType.HOTEL_SERVICE_CONTENT);
    }

    @Override
    public Long saveHotelAndServiceContents(MultipartFile coverImg, MultipartFile[] outsideImgs, MultipartFile[] innerImgs, Hotel hotel) {
        if(hotelMapper.getRepeatCount(hotel) > 0) {
            throw new BizExecption("酒店名称重复");
        }
        //上传封面图片
        if(coverImg != null && !coverImg.isEmpty()) {
            hotel.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.HOTEL_COVER_IMG_PATH));
        }
        //保存酒店
        hotelMapper.saveAndGetId(hotel);
        if(hotel.getId() != null) {
            // 保存酒店的外观图片
            if(ArrayUtils.isNotEmpty(outsideImgs)) {
                List<String> outsideImgUrls =  UploadUtils.uploadFiles(outsideImgs, UploadUtils.HOTEL_OUTSIDE_IMGS_PATH);
                hotelMapper.saveOutSideImgUrls(outsideImgUrls, hotel.getId());
            }
            //保存酒店的大厅图片
            if(ArrayUtils.isNotEmpty(innerImgs)) {
                List<String> innerImgUrls = UploadUtils.uploadFiles(innerImgs, UploadUtils.HOTEL_INNER_IMGS_PATH);
                hotelMapper.saveInnerImgUrls(innerImgUrls, hotel.getId());
            }
            // 保存酒店的服务内容
            if(hotel.getServiceContents() != null && hotel.getServiceContents().size() != 0) {
                hotelMapper.saveHotelContents(hotel);
            }
            return hotel.getId();
        }else {
            throw new RuntimeException();
        }
    }
    
    /**
	 * @see tech.youmu.ckl.service.IHotelService#saveCooperator(org.springframework.web.multipart.MultipartFile, org.springframework.web.multipart.MultipartFile[], tech.youmu.ckl.domain.Hotel)
	 */
	@Override
	public void saveCooperator(MultipartFile coverImg, MultipartFile[] outsideImgs, Hotel hotel) {
		
		//上传封面图片
        if(coverImg != null) {
            hotel.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.HOTEL_COVER_IMG_PATH));
        }
        //保存酒店
        hotel.setType(2); //设置为合作商家类型
        hotelMapper.saveAndGetId(hotel);
        if(hotel.getId() != null) {
            // 保存酒店的外观图片
            if(ArrayUtils.isNotEmpty(outsideImgs)) {
                List<String> outsideImgUrls =  UploadUtils.uploadFiles(outsideImgs, UploadUtils.HOTEL_OUTSIDE_IMGS_PATH);
                hotelMapper.saveOutSideImgUrls(outsideImgUrls, hotel.getId());
            }
        }else {
            throw new RuntimeException();
        }
	}

    @Override
    public void updateHotelAndServiceContents(MultipartFile coverImg, MultipartFile[] outsideImgs, MultipartFile[] innerImgs, Hotel hotel) {
        if(hotelMapper.getRepeatCount(hotel) > 0) {
            throw new BizExecption("酒店名称重复");
        }
        //上传封面图片
        if(coverImg != null) {
            hotel.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.HOTEL_COVER_IMG_PATH));
        }
        // 上传外观图片
        if(ArrayUtils.isNotEmpty(outsideImgs)) {
            List<String> outsideImgUrls =  UploadUtils.uploadFiles(outsideImgs, UploadUtils.HOTEL_OUTSIDE_IMGS_PATH);
            if(outsideImgUrls != null && outsideImgUrls.size() != 0) {
                hotelMapper.deleteOutSideImgUrls(hotel.getId());
                hotelMapper.saveOutSideImgUrls(outsideImgUrls, hotel.getId());
            }
        }
        // 删除大厅图片
        if(ArrayUtils.isNotEmpty(innerImgs)) {
            List<String> innerImgUrls = UploadUtils.uploadFiles(innerImgs, UploadUtils.HOTEL_INNER_IMGS_PATH);
            if(innerImgUrls != null && innerImgUrls.size() != 0) {
                hotelMapper.deleteInnerImgUrls(hotel.getId());
                hotelMapper.saveInnerImgUrls(innerImgUrls, hotel.getId());
            }
        }
        // 修改酒店
        hotelMapper.updateById(hotel);
        // 修改酒店内容
        hotelMapper.deleteHotelContents(hotel.getId());
        if(hotel.getServiceContents() != null && hotel.getServiceContents().size() != 0) {
            hotelMapper.saveHotelContents(hotel);
        }
    }
    
    /**
	 * @see tech.youmu.ckl.service.IHotelService#updateCooperator(org.springframework.web.multipart.MultipartFile, org.springframework.web.multipart.MultipartFile[], tech.youmu.ckl.domain.Hotel)
	 */
	@Override
	public void updateCooperator(MultipartFile coverImg, MultipartFile[] outsideImgs, Hotel hotel) {
		//上传封面图片
        if(coverImg != null) {
            hotel.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.HOTEL_COVER_IMG_PATH));
        }
        // 上传外观图片
        if(ArrayUtils.isNotEmpty(outsideImgs)) {
            List<String> outsideImgUrls =  UploadUtils.uploadFiles(outsideImgs, UploadUtils.HOTEL_OUTSIDE_IMGS_PATH);
            if(outsideImgUrls != null && outsideImgUrls.size() != 0) {
                hotelMapper.deleteOutSideImgUrls(hotel.getId());
                hotelMapper.saveOutSideImgUrls(outsideImgUrls, hotel.getId());
            }
        }
        // 修改酒店
        hotelMapper.updateById(hotel);
	}

    /**
     * @see tech.youmu.ckl.service.IHotelService#changeStatus(tech.youmu.ckl.domain.Hotel)
     */
    @Override
    public void changeStatus(Hotel hotel) {
        if (hotel!=null) {
            hotelMapper.changeStatus(hotel);
        }
    }

    @Override
    public List<HotelInfo> findHotelInfo(String startTime, String lng, String lat, Integer sort, Integer stars, Integer startPrice, Integer endPrice, String search,Long stationId) {
        List<HotelInfo> hotelInfos = new ArrayList<>();
        List<Hotel> hotels =  hotelMapper.findHotel(startTime,sort,stars,startPrice,endPrice,search,stationId);
        StringBuffer origins = new StringBuffer();
        StringBuffer destination = new StringBuffer();
        destination.append(lng).append(",").append(lat);
        for(Hotel hotel :hotels){
            if(hotel.getLongtitude() == null||hotel.getLongtitude() ==null){
                origins.append(0).append(",").append(0).append("|");
            }else{
                origins.append(hotel.getLongtitude()).append(",").append(hotel.getLatitude()).append("|");
            }
        }
        List<Double> distances = GaodeUtil.getDistances(origins.toString(), destination.toString(), 1);
        for(int i=0;i<hotels.size();i++){
        	if(distances.size()==0){
        		Hotel hotel = hotels.get(i);
                HotelInfo hotelInfo = new HotelInfo();
                hotelInfo.setHotelId(hotel.getId());
                hotelInfo.setHotelName(hotel.getName());
                hotelInfo.setStationName(hotel.getStationName());
                hotelInfo.setMinConsume(hotel.getMinimun());
                hotelInfo.setDistance(0);
                hotelInfo.setType(hotel.getType());
                hotelInfo.setStars(hotel.getStars());
                hotelInfo.setIsFull(hotel.getIsFull());
                hotelInfo.setCoverImg(ImageURLUtil.fillPath(hotel.getCover()));
                hotelInfos.add(hotelInfo);
        	}else{
	            double distance = distances.get(i);
	            if(distance>0){
	                Hotel hotel = hotels.get(i);
	                HotelInfo hotelInfo = new HotelInfo();
	                hotelInfo.setHotelId(hotel.getId());
	                hotelInfo.setHotelName(hotel.getName());
	                hotelInfo.setStationName(hotel.getStationName());
	                if(hotel.getMinimun() !=null){
	                    hotelInfo.setMinConsume(hotel.getMinimun());
	                }
	                hotelInfo.setDistance(distance/1000);
	                hotelInfo.setType(hotel.getType());
	                hotelInfo.setStars(hotel.getStars());
	                hotelInfo.setIsFull(hotel.getIsFull());
	                hotelInfo.setCoverImg(ImageURLUtil.fillPath(hotel.getCover()));
	                hotelInfos.add(hotelInfo);
	            }
	        }
        }
        if(sort ==1){
            Collections.sort(hotelInfos);
        }
        return hotelInfos;
    }

    @Override
    public HotelDetailInfo getHotelDetailInfo(Long hotelId, String startTime) {
        HotelDetailInfo hotelDetailInfo = hotelMapper.getHotelDetailInfo(hotelId,startTime);
        if(hotelDetailInfo == null){
        	throw new BizExecption("没有该酒店");
        }
        hotelDetailInfo.setCoverImg(ImageURLUtil.fillPath(hotelDetailInfo.getCoverImg()));
        for(HotelRoomInfo hotelRoomInfo:hotelDetailInfo.getRoomInfos()){
            hotelRoomInfo.setRoomCoverImg(ImageURLUtil.fillPath(hotelRoomInfo.getRoomCoverImg()));
            hotelRoomInfo.setRoomImgs(ImageURLUtil.fillPath(hotelRoomInfo.getRoomImgs()));
        }
        hotelDetailInfo.setHotelInnerImgs(ImageURLUtil.fillPath(hotelDetailInfo.getHotelInnerImgs()));
        hotelDetailInfo.setHotelOutSideImgs(ImageURLUtil.fillPath(hotelDetailInfo.getHotelOutSideImgs()));
        hotelDetailInfo.setHotelRoomImgs(ImageURLUtil.fillPath(hotelDetailInfo.getHotelRoomImgs()));
        return hotelDetailInfo;
    }



}
