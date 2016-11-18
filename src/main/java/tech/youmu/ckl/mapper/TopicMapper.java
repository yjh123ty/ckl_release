/**
 * Project Name:ckl
 * File Name:TopicMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.Topic;
import tech.youmu.ckl.query.TopicQuery;


/**
 * <p>Title:TopicMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月26日下午1:31:58</p>
 * <p>Description:帖子数据库操作接口</p>
 */
public interface TopicMapper {
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:44:19;</p>
     *	<p>Description: 根据查询条件获取论坛帖子列表; 默认按照置顶和时间降序排列。</p>
     *  @param query
     *  @return
     */
    public List<Topic> getForumTopicsByQuery(TopicQuery query);
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:44:19;</p>
     *	<p>Description: 根据查询条件获取论坛帖子总数。</p>
     *  @param query
     *  @return
     */
    public long getForumTopicsCountByQuery(TopicQuery query);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:47:00;</p>
     *	<p>Description: 根据帖子的Id来删除帖子 不论是否删除都将删除标记置为1;</p>
     *  @param id
     */
    public void deleteForumTopicById(Long id);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:48:25;</p>
     *	<p>Description: 置顶该Id的论坛帖子;</p>
     *  @param id
     */
    public void topForumTopicById(Long id);
    
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:44:19;</p>
     *  <p>Description: 根据查询条件获取伴侣帖子列表; 默认按照置顶和时间降序排列。</p>
     *  @param query
     *  @return
     */
    public List<Topic> getCompanionTopicsByQuery(TopicQuery query);
    
    /**
     * 
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:44:19;</p>
     *  <p>Description: 根据查询条件获取伴侣帖子总数。</p>
     *  @param query
     *  @return
     */
    public long getCompanionTopicsCountByQuery(TopicQuery query);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:47:00;</p>
     *  <p>Description: 根据帖子的Id来删除帖子 不论是否删除都将删除标记置为1;</p>
     *  @param id
     */
    public void deleteCompanionTopicById(Long id);
    
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月5日上午10:48:25;</p>
     *  <p>Description: 置顶该Id的伴侣帖子;</p>
     *  @param id
     */
    public void topCompanionTopicById(Long id);
   
}
