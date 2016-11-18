/**
 * @Title: IPerformanceService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月19日 下午2:03:12
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.domain.Performance;
import tech.youmu.ckl.query.PerformanceQuery;

/**
 * 员工绩效
 * @author youmu-yjh
 * 
 */
public interface IPerformanceService extends IBaseService<Performance>{
        /**
         * 
         *  <p>Author:yjh;</p>
         *  <p>Date:2016年9月22日下午6:03:49;</p>
         *	<p>Description: 下载绩效报表;</p>
         *  @param baseQuery
         *  @param request
         *  @param response
         *  @throws Exception
         */
    public void download(PerformanceQuery baseQuery,HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月17日下午2:27:58;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @return
     */
    public List<LastMonthPerformanceInfo> getLastMonthPerformanceInfo(Long employeeId);
}
