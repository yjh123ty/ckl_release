/**
 * @Title: PerformanceMapper.java
 * @Package tech.youmu.ckl.mapper
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月19日 下午2:12:21
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.LastMonthPerformanceInfo;
import tech.youmu.ckl.domain.Performance;

/**
 * 员工绩效
 * @author youmu-yjh
 * 
 */
public interface PerformanceMapper extends BaseMapper<Performance>{

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月17日下午2:35:30;</p>
     *	<p>Description: TODO;</p>
     *  @param employeeId
     *  @param lastMonth
     *  @return
     */
    public List<LastMonthPerformanceInfo> getLastMonthPerformanceInfo(@Param("employeeId")Long employeeId,@Param("lastMonth")String lastMonth);

}
