/**
 * @Title: IServiceEvaluationDetailService.java
 * @Package tech.youmu.ckl.service
 * 
 * @author yjh
 * @date 2016年8月30日 下午10:06:04
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.domain.ServiceEvaluationDetail;

/**
 * 服务评价明细 服务层接口
 * @author yjh
 *
 */
public interface IServiceEvaluationDetailService extends IBaseService<ServiceEvaluationDetail>{
	
    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月31日上午9:25:06;</p>
     *	<p>Description: TODO;</p>
     *  @param l
     */
    List<ServiceEvaluationDetail> getListByOrderId(Long orderId);
}
