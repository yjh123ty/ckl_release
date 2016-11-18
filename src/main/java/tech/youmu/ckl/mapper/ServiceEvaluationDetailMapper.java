/**
 * @Title: ServiceEvaluationDetailMapper.java
 * @Package tech.youmu.ckl.mapper
 * 
 * @author yjh
 * @date 2016年8月30日 下午9:20:32
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.domain.ServiceEvaluationDetail;

/**
 * 服务评价明细
 * @author yjh
 *
 */
public interface ServiceEvaluationDetailMapper extends BaseMapper<ServiceEvaluationDetail>{

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年8月31日上午9:28:58;</p>
     *	<p>Description: 根据订单id查询服务明细;</p>
     *  @param orderId
     *  @return
     */
    List<ServiceEvaluationDetail> getListByOrderId(Long orderId);

    void batchSaveServiceEvaluationDetail(@Param("orderId")Long orderId, @Param("list")List<ServiceEvaluationDetail> serviceEvaluationDetails);
	
}
