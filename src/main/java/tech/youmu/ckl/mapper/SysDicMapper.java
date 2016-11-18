/**
 * Project Name:ckl
 * File Name:SysDicMapper.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.SysDicDetail;
import tech.youmu.ckl.domain.SysDicType;

/**
 * <p>Title:SysDicMapper</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月30日上午10:53:59</p>
 * <p>Description:数据字典数据访问接口</p>
 */
public interface SysDicMapper {
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午10:54:49;</p>
     *	<p>Description: 保存一个数据字典类型 会将id回填到该对象中;</p>
     */
    public void saveType(SysDicType type);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月12日下午2:31:31;</p>
     *	<p>Description: 获取该name的数据字典数量;</p>
     *  @param name
     */
    public int countType(String name);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午10:55:40;</p>
     *	<p>Description: 保存一个数据字典详细;</p>
     *  @param detail
     */
    public void saveDetail(SysDicDetail detail);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午10:56:27;</p>
     *	<p>Description: 修改一个数据字典类型;</p>
     */
    public void updateType(SysDicType type);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午10:56:27;</p>
     *  <p>Description: 修改一个数据字典详细;</p>
     */
    public void updateDetail(SysDicDetail detail);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午10:57:57;</p>
     *	<p>Description: 设置数据字典类型的删除标志为删除;</p>
     *  @param id
     */
    public void deleteTypeById(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午10:57:57;</p>
     *  <p>Description: 设置数据字典详细的删除标志为删除;</p>
     *  @param detail
     */
    public void deleteDetailById(Long id);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年8月30日上午11:00:09;</p>
     *	<p>Description: 根据数据字典类型获取下面所有的数据字典详细;</p>
     *  @return
     */
    public List<SysDicDetail> getDetailsByTypeName(String typeName);
}
    
