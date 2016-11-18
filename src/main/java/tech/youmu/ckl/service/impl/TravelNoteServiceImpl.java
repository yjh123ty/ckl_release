/**
 * Project Name:ckl
 * File Name:TravelNoteServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.app.vo.TravelNoteContentInfo;
import tech.youmu.ckl.app.vo.TravelNoteDetailInfo;
import tech.youmu.ckl.app.vo.TravelNoteInfo;
import tech.youmu.ckl.app.vo.TravelNoteMonthInfo;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.ForumTopic;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.domain.TravelNoteContent;
import tech.youmu.ckl.mapper.ForumTopicMapper;
import tech.youmu.ckl.mapper.TravelNoteMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TravelNoteQuery;
import tech.youmu.ckl.service.ITravelNoteService;
import tech.youmu.ckl.utils.ImageURLUtil;
import tech.youmu.ckl.utils.ShareUtil;
import tech.youmu.ckl.utils.UploadUtils;

/**
 * 
 * <p>Title:TravelNoteServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月12日下午3:25:35</p>
 * <p>Description:TODO</p>
 */
@Service
public class TravelNoteServiceImpl implements ITravelNoteService{
    
    @Autowired
    private TravelNoteMapper travelNoteMapper;
    
    @Autowired
    private ForumTopicMapper forumTopicMapper;
    
    
    @Override
    public List<TravelNoteMonthInfo> findTravelNoteMonthInfo(Long userId) {
        
        List<TravelNoteMonthInfo> travelNoteMonthInfos = travelNoteMapper.findTravelNoteMonthInfo(userId);
        for(TravelNoteMonthInfo travelNoteMonthInfo:travelNoteMonthInfos){
            for(TravelNoteInfo travelNoteInfo:travelNoteMonthInfo.getTravelNoteInfos()){
                travelNoteInfo.setRouteImg(ImageURLUtil.fillPath(travelNoteInfo.getRouteImg()));
            }
                
        }
        return travelNoteMonthInfos;
    }

    @Override
    public TravelNoteDetailInfo getTravelNoteDetailInfo(Long travelNoteId,Long userRouteId) {
        TravelNoteDetailInfo travelNoteDetailInfo = travelNoteMapper.getTravelNoteDetailInfo(travelNoteId,userRouteId);
        if(travelNoteDetailInfo !=null){
            travelNoteDetailInfo.setShareUrl(ShareUtil.getTravelNoteShareUrl(travelNoteDetailInfo.getTravelNoteId()));
            for(TravelNoteContentInfo travelNoteContentInfo:travelNoteDetailInfo.getTravelNoteContentInfos()){
                if(StatusConst.TWO == travelNoteContentInfo.getType()){
                    travelNoteContentInfo.setContent(ImageURLUtil.fillPath(travelNoteContentInfo.getContent()));
                }
            }
        }
        return travelNoteDetailInfo;
    }

    @Override
    public void updateTravelNote(TravelNote travelNote) {
        travelNoteMapper.updateTravelNote(travelNote);
        if(travelNote.getTravelNoteContents() !=null &&travelNote.getTravelNoteContents().size()>0){
            for(TravelNoteContent travelNoteContent : travelNote.getTravelNoteContents()){
                if(StatusConst.TWO ==travelNoteContent.getType()&&travelNoteContent.getImg() !=null){
                    String path = UploadUtils.uploadFile(travelNoteContent.getImg(), UploadUtils.TRAVEL_NOTE);
                    travelNoteContent.setContent(path);
                }else if(StatusConst.TWO ==travelNoteContent.getType()&&travelNoteContent.getId() !=null){
                    TravelNoteContent dbTravelNoteContent = travelNoteMapper.getTravelNoteContentById(travelNoteContent.getId());
                    travelNoteContent.setContent(dbTravelNoteContent.getContent());
                }
            }
            travelNoteMapper.deleteTravelNoteContentByTravelNoteId(travelNote.getId());
            travelNoteMapper.batchSaveTravelNoteContent(travelNote.getTravelNoteContents(),travelNote.getId());
        }
        List<String> suitNames = travelNote.getSuitNames();
        if(suitNames!=null&&suitNames.size()>0){
            travelNoteMapper.deleteTravelNoteSuitByTravelNoteId(travelNote.getId());
            travelNoteMapper.batchSaveTravelNoteSuit(suitNames, travelNote.getId());
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITravelNoteService#getPageList(tech.youmu.ckl.query.TravelNoteQuery)
     */
    @Override
    public PageList<TravelNote> getPageList(TravelNoteQuery query) {
        PageList<TravelNote> pageList = new PageList<TravelNote>();
        long count = travelNoteMapper.getCountByQuery(query);
        if(count > 0) {
            pageList.setTotal(count);
            pageList.setRows(travelNoteMapper.getByQuery(query));
        }
        return pageList;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITravelNoteService#deleteUserTravelNote(java.lang.Long)
     */
    @Override
    public void deleteUserTravelNote(Long id) {
        travelNoteMapper.deleteUserTravelNote(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITravelNoteService#getTravelNoteById(java.lang.Long)
     */
    @Override
    public TravelNote getTravelNoteById(Long id) {
        return travelNoteMapper.getTravelNoteById(id);
    }

    @Override
    public void shareTravelNote(Long travelNoteId,Long userId) {
        TravelNote travelNote =travelNoteMapper.getById(travelNoteId);
        ForumTopic forumTopic = new ForumTopic(userId,travelNoteId,travelNote.getTitle(),travelNote.getRouteImg(),StatusConst.THREE);
        forumTopicMapper.save(forumTopic);
    }

}
