/**
 * Project Name:ckl
 * File Name:ITopicService.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.Topic;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TopicQuery;

/**
 * <p>Title:ITopicService</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月26日下午2:31:21</p>
 * <p>Description:帖子服务接口</p>
 */
public interface ITopicService{
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:50:50;</p>
     *	<p>Description: 提供论坛帖子分页查询的接口;</p>
     *  @param query
     *  @return
     */
    public PageList<Topic> getForumTopicPageList(TopicQuery query);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:51:32;</p>
     *	<p>Description: 提供论坛帖子删除的接口;</p>
     *  @param id
     */
    public void deleteForumTopicById(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:52:20;</p>
     *	<p>Description: 提供论坛帖子置顶的接口;</p>
     *  @param id
     */
    public void topForumTopicById(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:50:50;</p>
     *  <p>Description: 提供伴侣帖子分页查询的接口;</p>
     *  @param query
     *  @return
     */
    public PageList<Topic> getCompanionTopicPageList(TopicQuery query);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:51:32;</p>
     *  <p>Description: 提供伴侣帖子删除的接口;</p>
     *  @param id
     */
    public void deleteCompanionTopicById(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:52:20;</p>
     *  <p>Description: 提供伴侣帖子置顶的接口;</p>
     *  @param id
     */
    public void topCompanionTopicById(Long id);
    
}
