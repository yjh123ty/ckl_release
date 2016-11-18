/**
 * @Title: DepartmentServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月10日 下午6:09:32
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.constants.Global;
import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.DepartmentMapper;
import tech.youmu.ckl.query.DepartmentQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IDepartmentService;

/**
 * @author Administrator
 *
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {
	private DepartmentMapper departmentMapper;

	/**
	 * @param departmentService
	 */
	@Autowired
	public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
		super(departmentMapper);
		this.departmentMapper = departmentMapper;
	}

	/* 
	 * @see tech.youmu.ckl.service.IDepartmentService#disableById(java.lang.Long)
	 */
	@Override
	public void disableById(Long id) {
		Department department = (Department) departmentMapper.getById(id);
		if(department!=null){
			new BizExecption("部门记录为空！");
		}
		if(department.getStatus()==StatusConst.BLOCKED){
			new BizExecption("该部门记录已经冻结！");
		}
		if(department.getStatus()!=null){
			departmentMapper.disableById(id);
		}
	}

    /* 
     * @see tech.youmu.ckl.service.IDepartmentService#pageList(tech.youmu.ckl.query.DepartmentQuery)
     */
    @Override
    public PageList<Department> pageList(DepartmentQuery departmentQuery) {
        List<Department> list = departmentMapper.queryList(departmentQuery);
        Long count = departmentMapper.getCount(departmentQuery);
        PageList<Department> pageList = new PageList<Department>();
        pageList.setRows(list);
        pageList.setTotal(count);
        return pageList;
    }
}
