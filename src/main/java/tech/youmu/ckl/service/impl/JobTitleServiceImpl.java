/**
 * @Title: JobTitleServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月16日 上午10:37:07
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.JobTitle;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.JobTitleMapper;
import tech.youmu.ckl.query.JobTitleQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IJobTitleService;

/**
 * 职位管理服务层
 * @author youmu-yjh
 * 
 */
@Service
public class JobTitleServiceImpl extends BaseServiceImpl<JobTitle> implements IJobTitleService {

    private JobTitleMapper jobTitleMapper;
    
    
    /**
     * @param jobTitleMapper
     */
	@Autowired
    public JobTitleServiceImpl(JobTitleMapper jobTitleMapper) {
        super(jobTitleMapper);
        this.jobTitleMapper = jobTitleMapper;
    }

	/* (non-Javadoc)
	 * @see tech.youmu.ckl.service.impl.BaseServiceImpl#save(java.lang.Object)
	 */
	@Override
	public boolean save(JobTitle jobTitle) {
	    if(jobTitle.getName()==null){
	        throw new BizExecption("请填写岗位名称！");
	    }
	    if(jobTitle.getRatio()==null){
            throw new BizExecption("请填写岗位月绩效奖金！");
        }
	    return super.save(jobTitle);
	}
	
    /* 
     * @see tech.youmu.ckl.service.IJobTitleService#pageList(tech.youmu.ckl.query.JobTitleQuery)
     */
    @Override
    public PageList<JobTitle> pageList(JobTitleQuery jobTitleQuery) {
        List<JobTitle> list = jobTitleMapper.queryList(jobTitleQuery);
        Long count = jobTitleMapper.getCount(jobTitleQuery);
        PageList<JobTitle> pageList = new PageList<JobTitle>();
        pageList.setRows(list);
        pageList.setTotal(count);
        return pageList;
    }

    
}
