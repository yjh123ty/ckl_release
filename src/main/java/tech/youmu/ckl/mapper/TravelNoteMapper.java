/**
 * Project Name:ckl
 * File Name:TravelNoteMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.TravelNoteDetailInfo;
import tech.youmu.ckl.app.vo.TravelNoteMonthInfo;
import tech.youmu.ckl.domain.TemplateTravelNote;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.domain.TravelNoteContent;

/**
 * 
 * <p>Title:TravelNoteMapper</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年9月12日下午3:26:33</p>
 * <p>Description:游记</p>
 */
public interface TravelNoteMapper extends BaseMapper<TravelNote> {
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午3:31:06;</p>
     *	<p>Description: 轨迹列表;</p>
     *  @param userId
     *  @return
     */
    List<TravelNoteMonthInfo> findTravelNoteMonthInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午4:12:55;</p>
     *	<p>Description: TODO;</p>
     *  @param travelNoteId
     *  @return
     */
    TravelNoteDetailInfo getTravelNoteDetailInfo(@Param("travelNoteId")Long travelNoteId,@Param("userRouteId")Long userRouteId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午5:18:33;</p>
     *	<p>Description: 更新游记;</p>
     *  @param travelNote
     */
    void updateTravelNote(TravelNote travelNote);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午6:19:37;</p>
     *	<p>Description: TODO;</p>
     *  @param travelNoteId
     */
    void deleteTravelNoteContentByTravelNoteId(Long travelNoteId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月12日下午6:19:41;</p>
     *	<p>Description: TODO;</p>
     *  @param travelNoteContents
     */
    void batchSaveTravelNoteContent(@Param("list")List<TravelNoteContent> travelNoteContents,@Param("travelNoteId")Long travelNoteId);

    void batchSaveTravelNoteSuit(@Param("suitNames")List<String> suitNames,@Param("travelNoteId")Long travelNoteId);

    void deleteTravelNoteSuitByTravelNoteId(Long travelNoteId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月26日上午9:50:07;</p>
     *	<p>Description: TODO;</p>
     *  @param userRouteId
     *  @return
     */
    TravelNote getByUserRouteId(Long userRouteId);

    void saveTravelNote(TravelNote travelNote);
    
    void deleteUserTravelNote(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日下午4:26:57;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     *  @return
     */
    TravelNote getTravelNoteById(Long id);

    TravelNoteContent getTravelNoteContentById(Long id);
    
}
