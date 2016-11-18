/**
 * @Title: DepartmentMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月10日 下午5:47:11
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.Department;
import tech.youmu.ckl.query.DepartmentQuery;

/**
 * 部门管理
 * @author yjh
 *
 */
@SuppressWarnings("rawtypes")
public interface DepartmentMapper extends BaseMapper {
	
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
    public List<Department> queryList(DepartmentQuery departmentQuery);

    /**
     * 符合查询条件的部门列表数
     * @param departmentQuery
     * @return
     */
    public Long getCount(DepartmentQuery departmentQuery);
	
}
