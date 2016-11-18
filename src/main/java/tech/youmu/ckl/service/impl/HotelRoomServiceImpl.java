/**
 * Project Name:ckl
 * File Name:HotelRoomServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.formula.eval.UnaryPlusEval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.HotelRoom;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.HotelMapper;
import tech.youmu.ckl.mapper.HotelRoomMapper;
import tech.youmu.ckl.service.IHotelRoomService;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:HotelRoomServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月9日下午3:14:28</p>
 * <p>Description:TODO</p>
 */
@Service
public class HotelRoomServiceImpl implements IHotelRoomService {

    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomService#getHotelRooms(java.lang.Long)
     */
    @Override
    public List<HotelRoom> getHotelRooms(Long hotelId) {
        if(hotelId == null) {
            return new ArrayList<HotelRoom>(0);
        }
        return hotelRoomMapper.getHotelRooms(hotelId);
    }

    @Override
    public void save(MultipartFile coverImg, MultipartFile[] images, HotelRoom room) {
        if(hotelRoomMapper.getRepeatCount(room) > 0){
            throw new BizExecption("房间名称重复");
        }
        // 上传封面图片
        if(coverImg != null && !coverImg.isEmpty()){
            room.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.HOTEL_ROOM_COVER_IMG_PATH));
        }
        hotelRoomMapper.save(room);
        if(room.getId() != null) {
            if(VerifyUtil.isNotEmptyMultipartFiles(images)) {
                List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.HOTEL_ROOM_IMGS_PATH);
                if(urls != null && urls.size() != 0) {
                    hotelRoomMapper.saveImgsUrls(urls, room.getId());
                }
            }
        }
    }

    @Override
    public void update(MultipartFile coverImg, MultipartFile[] images, HotelRoom room) {
        if(hotelRoomMapper.getRepeatCount(room) > 0){
            throw new BizExecption("房间名称重复");
        }
        // 上传封面图片
        if(coverImg != null && !coverImg.isEmpty()){
            room.setCover(UploadUtils.uploadFile(coverImg, UploadUtils.HOTEL_ROOM_COVER_IMG_PATH));
        }
       hotelRoomMapper.update(room);
       if(VerifyUtil.isNotEmptyMultipartFiles(images)) {
           List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.HOTEL_ROOM_IMGS_PATH);
           if(urls != null && urls.size() != 0) {
               hotelRoomMapper.deleteImgsUrlsByHotelRoomId(room.getId());
               hotelRoomMapper.saveImgsUrls(urls, room.getId());
           }
       }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
        hotelRoomMapper.delete(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.IHotelRoomService#getAll()
     */
    @Override
    public List<HotelRoom> getAll() {
        return hotelRoomMapper.getAll();
    }
}
