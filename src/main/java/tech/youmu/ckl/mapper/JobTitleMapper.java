/**
 * @Title: JobTitleMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月16日 上午10:30:41
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import tech.youmu.ckl.domain.JobTitle;
import tech.youmu.ckl.query.JobTitleQuery;

/**
 * 员工职位
 * @author yjh
 *
 */
public interface JobTitleMapper extends BaseMapper {
    /**
     * 职位列表
     * @param jobTitleQuery
     * @return
     */
    public List<JobTitle> queryList(JobTitleQuery jobTitleQuery);

    /**
     * 符合查询条件的职位列表数
     * @param jobTitleQuery
     * @return
     */
    public Long getCount(JobTitleQuery jobTitleQuery);
}
