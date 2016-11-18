/**
 * Project Name:ckl
 * File Name:TopicServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.Topic;
import tech.youmu.ckl.mapper.TopicMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TopicQuery;
import tech.youmu.ckl.service.ITopicService;

/**
 * <p>Title:TopicServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月26日下午2:32:20</p>
 * <p>Description:帖子服务实现类</p>
 */
@Service
public class TopicServiceImpl  implements ITopicService {

    @Autowired
    private TopicMapper topicMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITopicService#getForumTopicPageList(tech.youmu.ckl.query.TopicQuery)
     */
    @Override
    public PageList<Topic> getForumTopicPageList(TopicQuery query) {
        PageList<Topic> result = new PageList<Topic>();
        // 查询数据的条数
        long count = topicMapper.getForumTopicsCountByQuery(query);
        // 如果数据的条数大于0
        if(count > 0) {
            // 查询数据页 
            result.setTotal(count);
            result.setRows(topicMapper.getForumTopicsByQuery(query));
        }
        // 封装分页对象
        return result;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITopicService#deleteForumTopicById(java.lang.Long)
     */
    @Override
    public void deleteForumTopicById(Long id) {
        topicMapper.deleteForumTopicById(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITopicService#topForumTopicById(java.lang.Long)
     */
    @Override
    public void topForumTopicById(Long id) {
        topicMapper.topForumTopicById(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITopicService#getCompanionTopicPageList(tech.youmu.ckl.query.TopicQuery)
     */
    @Override
    public PageList<Topic> getCompanionTopicPageList(TopicQuery query) {
        PageList<Topic> result = new PageList<Topic>();
        // 查询数据的条数
        long count = topicMapper.getCompanionTopicsCountByQuery(query);
        // 如果数据的条数大于0
        if(count > 0) {
            // 查询数据页 
            result.setTotal(count);
            result.setRows(topicMapper.getCompanionTopicsByQuery(query));
        }
        // 封装分页对象
        return result;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITopicService#deleteCompanionTopicById(java.lang.Long)
     */
    @Override
    public void deleteCompanionTopicById(Long id) {
        topicMapper.deleteCompanionTopicById(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITopicService#topCompanionTopicById(java.lang.Long)
     */
    @Override
    public void topCompanionTopicById(Long id) {
        topicMapper.topCompanionTopicById(id);
    }

}
