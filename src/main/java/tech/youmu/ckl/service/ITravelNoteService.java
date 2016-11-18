/**
 * Project Name:ckl
 * File Name:ITravelService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.TravelNoteDetailInfo;
import tech.youmu.ckl.app.vo.TravelNoteMonthInfo;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TravelNoteQuery;

/**
 * 
 * <p>Title:ITravelNoteService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月12日下午3:25:41</p>
 * <p>Description:用户游记服务</p>
 */
public interface ITravelNoteService {
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午10:06:34;</p>
     *	<p>Description: 用户游记后台分页展示;</p>
     *  @param query
     *  @return
     */
    public PageList<TravelNote> getPageList(TravelNoteQuery query);
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午3:24:21;</p>
     *	<p>Description: 游记月份列表;</p>
     *  @param userId
     *  @return
     */
    public List<TravelNoteMonthInfo> findTravelNoteMonthInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午4:11:58;</p>
     *	<p>Description: 游记明细;</p>
     *  @param travelNoteId
     *  @return
     */
    public TravelNoteDetailInfo getTravelNoteDetailInfo(Long travelNoteId,Long routeId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午5:09:16;</p>
     *	<p>Description: 更新游记;</p>
     *  @param travelNote
     */
    public void updateTravelNote(TravelNote travelNote);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:14:27;</p>
     *	<p>Description: 删除用户的一个游记;</p>
     *  @param id
     */
    public void deleteUserTravelNote(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日下午4:26:00;</p>
     *	<p>Description: 查询游记;</p>
     *  @param id
     *  @return
     */
    public TravelNote getTravelNoteById(Long id);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月12日上午11:22:09;</p>
     *	<p>Description: 游记分享;</p>
     *  @param travelNoteId
     */
    public void shareTravelNote(Long travelNoteId,Long userId);

}
