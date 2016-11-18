/**
 * Project Name:ckl
 * File Name:ITemplateTravelNoteService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.TemplateTravelNote;
import tech.youmu.ckl.domain.TemplateTravelNoteContent;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.domain.TravelNoteContent;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TravelNoteQuery;

/**
 * <p>Title:ITemplateTravelNoteService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月30日上午11:31:00</p>
 * <p>Description:模板游记的服务接口</p>
 */
public interface ITemplateTravelNoteService {

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:36:12;</p>
     *	<p>Description: 模板游记分页展示;</p>
     *  @param query
     *  @return
     */
    PageList<TravelNote> getPageList(TravelNoteQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:36:32;</p>
     *	<p>Description: 删除一个模板游记;</p>
     *  @param id
     */
    void deleteTemplateTravelNote(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:36:52;</p>
     *	<p>Description: 添加一条路线的模板游记;</p>
     *  @param travelNote
     */
    void save(TemplateTravelNote travelNote);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:37:08;</p>
     *	<p>Description: 修改一条路线的模板游记;</p>
     *  @param travelNote
     */
    void update(TemplateTravelNote travelNote);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午2:16:44;</p>
     *	<p>Description: 根据路线id获取模板游记的内容;</p>
     *  @param id
     * @return 
     */
    TemplateTravelNote createBaseTemplateTravelNoteByRouteId(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午4:00:48;</p>
     *	<p>Description: 查询一条路线的游记信息;</p>
     *  @param id
     *  @return
     */
    TemplateTravelNote getTemplateTravelNoteByRouteId(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午4:11:36;</p>
     *	<p>Description: TODO;</p>
     *  @param routeId
     *  @return
     */
    TemplateTravelNote createTemplateTravelNote(Long routeId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午7:12:49;</p>
     *	<p>Description: 保存一条游记的内容;</p>
     *  @param content
     */
    void saveContent(TemplateTravelNoteContent content);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午7:13:07;</p>
     *	<p>Description: 修改一个游记的内容;</p>
     *  @param content
     */
    void updateContent(TemplateTravelNoteContent content);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日上午11:40:17;</p>
     *	<p>Description: 查询该模板路线的内容;</p>
     *  @param id
     *  @return
     */
    TemplateTravelNoteContent getTemplateTravelNoteContentById(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日下午1:26:51;</p>
     *	<p>Description: 删除模板游记;</p>
     *  @param id
     */
    void deleteContentById(Long id);

}
