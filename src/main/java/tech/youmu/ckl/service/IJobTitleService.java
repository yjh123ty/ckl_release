/**
 * @Title: IJobTitleService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-Administrator
 * @date 2016年8月16日 上午10:27:41
 * @version V1.0
 */

package tech.youmu.ckl.service;

import tech.youmu.ckl.domain.JobTitle;
import tech.youmu.ckl.query.JobTitleQuery;
import tech.youmu.ckl.query.PageList;

/**
 * 员工职位管理服务层
 * @author yjh
 *
 */
public interface IJobTitleService extends IBaseService<JobTitle>{
    /**
     * 员工职位列表
     * @param departmentQuery
     * @return
     */
    public PageList<JobTitle> pageList(JobTitleQuery jobTitleQuery);
}
