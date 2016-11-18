/**
 * @Title: IDepartmentService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月10日 下午5:56:18
 * @version V1.0
 */

package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.query.DepartmentQuery;
import tech.youmu.ckl.query.PageList;

/**
 * 部门管理服务层
 * @author yjh
 *
 */
public interface IDepartmentService extends IBaseService<Department>{
	/**
	 * 禁用部门
	 * @param id
	 */
	public void disableById(Long id);

    /**
     * 部门列表
     * @param departmentQuery
     * @return
     */
    public PageList<Department> pageList(DepartmentQuery departmentQuery);	
}
