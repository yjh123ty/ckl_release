/**
 * @Title: ServiceEvaluationDetailServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * 
 * @author yjh
 * @date 2016年8月30日 下午10:07:14
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.ServiceEvaluationDetail;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.ServiceEvaluationDetailMapper;
import tech.youmu.ckl.service.IServiceEvaluationDetailService;

/**
 * @author yjh
 *
 */
@Service
public class ServiceEvaluationDetailServiceImpl extends BaseServiceImpl<ServiceEvaluationDetail> implements IServiceEvaluationDetailService{

	private ServiceEvaluationDetailMapper serviceEvaluationDetailMapper;
	
	@Autowired
	public ServiceEvaluationDetailServiceImpl(ServiceEvaluationDetailMapper serviceEvaluationDetailMapper) {
		super(serviceEvaluationDetailMapper);
		this.serviceEvaluationDetailMapper = serviceEvaluationDetailMapper;
	}

    /**
     * @see tech.youmu.ckl.service.IServiceEvaluationDetailService#getListById(java.lang.Long)
     */
    @Override
    public List<ServiceEvaluationDetail> getListByOrderId(Long orderId) {
        return serviceEvaluationDetailMapper.getListByOrderId(orderId);
    }

}
