/**
 * Project Name:ckl
 * File Name:TemplateTravelNoteServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.TemplateTravelNote;
import tech.youmu.ckl.domain.TemplateTravelNoteContent;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.mapper.TemplateTravelNoteMapper;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.TravelNoteQuery;
import tech.youmu.ckl.service.ITemplateTravelNoteService;

/**
 * <p>Title:TemplateTravelNoteServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年9月30日上午11:37:45</p>
 * <p>Description:TODO</p>
 */
@Service
public class TemplateTravelNoteServiceImpl implements ITemplateTravelNoteService {
    
    @Autowired
    private TemplateTravelNoteMapper travelNoteMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#getPageList(tech.youmu.ckl.query.TravelNoteQuery)
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
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#deleteTemplateTravelNote(java.lang.Long)
     */
    @Override
    public void deleteTemplateTravelNote(Long id) {
        travelNoteMapper.deleteTemplateTravelNote(id);
        travelNoteMapper.deleteTemplateTravelNoteSuit(id);
        travelNoteMapper.deleteTemplateTravelNoteContent(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#save(tech.youmu.ckl.domain.TemplateTravelNote)
     */
    @Override
    public void save(TemplateTravelNote travelNote) {
        travelNoteMapper.save(travelNote);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#update(tech.youmu.ckl.domain.TemplateTravelNote)
     */
    @Override
    public void update(TemplateTravelNote travelNote) {
        travelNoteMapper.update(travelNote);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#createBaseTemplateTravelNoteByRouteId(java.lang.Long)
     */
    @Override
    public TemplateTravelNote createBaseTemplateTravelNoteByRouteId(Long id) {
        return travelNoteMapper.createBaseTemplateTravelNoteByRouteId(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#getTemplateTravelNoteByRouteId(java.lang.Long)
     */
    @Override
    public TemplateTravelNote getTemplateTravelNoteByRouteId(Long id) {
        return travelNoteMapper.getTemplateTravelNoteByRouteId(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#createTemplateTravelNote(java.lang.Long)
     */
    @Override
    public TemplateTravelNote createTemplateTravelNote(Long routeId) {
        TemplateTravelNote travelNote = travelNoteMapper.createBaseTemplateTravelNoteByRouteId(routeId);
        travelNote.setRouteId(routeId);
        travelNoteMapper.save(travelNote);
        if(travelNote.getSuits() != null && travelNote.getSuits().size()!=0){
            travelNoteMapper.saveTemplateTravelNoteSuits(travelNote);
        }
        return travelNote;
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#saveContent(tech.youmu.ckl.domain.TemplateTravelNoteContent)
     */
    @Override
    public void saveContent(TemplateTravelNoteContent content) {
        travelNoteMapper.saveContent(content);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#updateContent(tech.youmu.ckl.domain.TemplateTravelNoteContent)
     */
    @Override
    public void updateContent(TemplateTravelNoteContent content) {
        travelNoteMapper.updateContent(content);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#getTemplateTravelNoteContentById(java.lang.Long)
     */
    @Override
    public TemplateTravelNoteContent getTemplateTravelNoteContentById(Long id) {
        return travelNoteMapper.getTemplateTravelNoteContentById(id);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ITemplateTravelNoteService#deleteContentById(java.lang.Long)
     */
    @Override
    public void deleteContentById(Long id) {
        travelNoteMapper.deleteTemplateTravelNoteContent(id);
    }

}
