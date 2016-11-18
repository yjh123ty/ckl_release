/**
 * Project Name:ckl
 * File Name:SysDicServiceImpl.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.SysDicDetail;
import tech.youmu.ckl.domain.SysDicType;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.SysDicMapper;
import tech.youmu.ckl.service.ISysDicService;

/**
 * <p>Title:SysDicServiceImpl</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年8月30日上午11:46:20</p>
 * <p>Description:数据字典服务</p>
 */
@Service
public class SysDicServiceImpl implements ISysDicService{
    
    @Autowired
    private SysDicMapper sysDicMapper;

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#saveType(tech.youmu.ckl.domain.SysDicType)
     */
    @Override
    public void saveType(SysDicType type) {
       if(sysDicMapper.countType(type.getName()) == 0){
           sysDicMapper.saveType(type);
       } else {
           throw new BizExecption("名称重复");
       }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#saveDetail(tech.youmu.ckl.domain.SysDicDetail)
     */
    @Override
    public void saveDetail(SysDicDetail detail) {
        sysDicMapper.saveDetail(detail);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#updateType(tech.youmu.ckl.domain.SysDicType)
     */
    @Override
    public void updateType(SysDicType type) {
        if(sysDicMapper.countType(type.getName()) == 0){
            sysDicMapper.updateType(type);
            if(type.getDetails().size() != 0) {
                for (SysDicDetail detail : type.getDetails()) {
                    sysDicMapper.saveDetail(detail);
                }
            }
        }else {
            throw new BizExecption("名称重复");
        }
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#updateDetail(tech.youmu.ckl.domain.SysDicDetail)
     */
    @Override
    public void updateDetail(SysDicDetail detail) {
        sysDicMapper.updateDetail(detail);
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#deleteTypeById(java.lang.Long)
     */
    @Override
    public void deleteTypeById(Long id) {
        sysDicMapper.deleteTypeById(id);
        
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#deleteDetailById(java.lang.Long)
     */
    @Override
    public void deleteDetailById(Long id) {
        sysDicMapper.deleteDetailById(id);
        
    }

    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISysDicService#getDetailsByTypeName(java.lang.String)
     */
    @Override
    public List<SysDicDetail> getDetailsByTypeName(String typeName) {
        return sysDicMapper.getDetailsByTypeName(typeName);
    }
    
    
}
