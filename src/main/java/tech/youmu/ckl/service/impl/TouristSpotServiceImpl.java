/**
 * Project Name:ckl
 * File Name:TouristSpotServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.domain.Station;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.TouristSpotMapper;
import tech.youmu.ckl.service.ITouristSpotService;
import tech.youmu.ckl.utils.GaodeUtil;
import tech.youmu.ckl.utils.UploadUtils;
import tech.youmu.ckl.utils.VerifyUtil;

/**
 * <p>Title:TouristSpotServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月22日下午2:30:30</p>
 * <p>Description:TODO</p>
 */
@Service
public class TouristSpotServiceImpl extends BaseServiceImpl<Station> implements ITouristSpotService {

    private TouristSpotMapper touristSpotMapper;

    @Autowired
    public TouristSpotServiceImpl(TouristSpotMapper touristSpotMapper) {
        super(touristSpotMapper);
        this.touristSpotMapper = touristSpotMapper;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITouristSpotService#save(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.Station)
     */
    @Override
    public void save(MultipartFile[] images, Station station) {
        if(checkRepeat(station)){
            throw new BizExecption("景点名称已经存在");
        }
        String[] coordinate = GaodeUtil.getCoordinateByAddress(station.getFullAddress());
        station.setLongtitude(coordinate[0]);
        station.setLatitude(coordinate[1]);
        super.save(station);
        List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.STATION_IMG);
        if(urls != null && urls.size() != 0) {
            touristSpotMapper.saveImgsUrls(station.getId(), urls);
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITouristSpotService#update(org.springframework.web.multipart.MultipartFile, tech.youmu.ckl.domain.Station)
     */
    @Override
    public void update(MultipartFile[] images, Station station) {
        if(checkRepeat(station)){
            throw new BizExecption("景点名称已经存在");
        }
        String[] coordinate = GaodeUtil.getCoordinateByAddress(station.getFullAddress());
        station.setLongtitude(coordinate[0]);
        station.setLatitude(coordinate[1]);
        super.updateById(station);
        if(VerifyUtil.isNotEmptyMultipartFiles(images)){
            List<String> urls = UploadUtils.uploadFiles(images, UploadUtils.STATION_IMG);
            if(urls != null && urls.size() != 0) {
                touristSpotMapper.deleteImgsUrls(station.getId());
                touristSpotMapper.saveImgsUrls(station.getId(), urls);
            }
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITouristSpotService#checkRepeat(tech.youmu.ckl.domain.Station)
     */
    @Override
    public boolean checkRepeat(Station station) {
        return touristSpotMapper.getRepeatCount(station) > 0 ? true : false;
    }
}
