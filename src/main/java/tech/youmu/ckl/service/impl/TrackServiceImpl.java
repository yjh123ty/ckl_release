/**
 * @Title: UserServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:58:35
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.TrackCoordInfo;
import tech.youmu.ckl.app.vo.TrackDetailInfo;
import tech.youmu.ckl.app.vo.TrackInfo;
import tech.youmu.ckl.app.vo.TrackMonthInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.ForumTopic;
import tech.youmu.ckl.domain.Track;
import tech.youmu.ckl.mapper.ForumTopicMapper;
import tech.youmu.ckl.mapper.TrackMapper;
import tech.youmu.ckl.service.ITrackService;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.ShareUtil;

/**
 * 
 * <p>Title:TrackServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年8月24日下午5:41:56</p>
 * <p>Description:TODO</p>
 */
@Service
public class TrackServiceImpl  implements ITrackService {
	
	
	@Autowired
	private TrackMapper trackMapper;
	
	@Autowired
    private ForumTopicMapper forumTopicMapper;

	@Override
	public List<TrackMonthInfo> findTrackMonthInfo(Long userId) {
	    List<TrackMonthInfo> trackMonthInfos =  trackMapper.findTrackMonthInfo(userId);
	    for(TrackMonthInfo trackMonthInfo:trackMonthInfos){
	        for(TrackInfo trackInfo:trackMonthInfo.getTrackInfos()){
	            trackInfo.setRouteImg(ImageURLUtil.fillPath(trackInfo.getRouteImg()));
	        }
	            
	    }
        return trackMonthInfos;
	}

    @Override
    public TrackDetailInfo getTrackDetailInfo(Long trackId,Long userRouteId) {
        TrackDetailInfo trackDetailInfo =  trackMapper.getTrackDetailInfo(trackId,userRouteId);
        if(trackDetailInfo !=null){
            trackDetailInfo.setShareUrl(ShareUtil.getTrackShareUrl(trackDetailInfo.getTrackId()));
        }
        return trackDetailInfo;
    }

    @Override
    public void shareTrack(Long trackId, Long userId) {
        Track track =(Track) trackMapper.getById(trackId);
        ForumTopic forumTopic = new ForumTopic(userId,trackId,track.getStartName()+"->"+track.getEndName(),track.getRouteImg(),StatusConst.TWO);
        forumTopicMapper.save(forumTopic);
    }







}
