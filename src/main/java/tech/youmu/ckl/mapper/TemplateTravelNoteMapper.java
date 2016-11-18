package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.RouteSuit;
import tech.youmu.ckl.domain.TemplateTravelNote;
import tech.youmu.ckl.domain.TemplateTravelNoteContent;
import tech.youmu.ckl.domain.TravelNote;
import tech.youmu.ckl.query.TravelNoteQuery;

public interface TemplateTravelNoteMapper {

    TemplateTravelNote getTemplateTravelNoteByRouteId(Long routeId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:40:37;</p>
     *	<p>Description: TODO;</p>
     *  @param query
     *  @return
     */
    long getCountByQuery(TravelNoteQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:40:49;</p>
     *	<p>Description: TODO;</p>
     *  @param query
     *  @return
     */
    List<TravelNote> getByQuery(TravelNoteQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:40:54;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    void deleteTemplateTravelNote(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:40:54;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    void deleteTemplateTravelNoteSuit(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:40:54;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     */
    void deleteTemplateTravelNoteContent(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:41:07;</p>
     *	<p>Description: TODO;</p>
     *  @param travelNote
     */
    void save(TemplateTravelNote travelNote);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日上午11:41:12;</p>
     *	<p>Description: TODO;</p>
     *  @param travelNote
     */
    void update(TemplateTravelNote travelNote);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午2:18:44;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     *  @return
     */
    TemplateTravelNote createBaseTemplateTravelNoteByRouteId(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午7:14:05;</p>
     *	<p>Description: TODO;</p>
     *  @param content
     */
    void saveContent(TemplateTravelNoteContent content);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年9月30日下午7:14:14;</p>
     *	<p>Description: TODO;</p>
     *  @param content
     */
    void updateContent(TemplateTravelNoteContent content);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日上午11:41:43;</p>
     *	<p>Description: TODO;</p>
     *  @param id
     *  @return
     */
    TemplateTravelNoteContent getTemplateTravelNoteContentById(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日下午1:24:32;</p>
     *	<p>Description: 删除模板游记的内容;</p>
     *  @param id
     */
    void deleteContentById(Long id);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月8日下午1:34:35;</p>
     *	<p>Description: 新增;</p>
     *  @param id
     *  @param suits
     */
    void saveTemplateTravelNoteSuits(TemplateTravelNote note);
}