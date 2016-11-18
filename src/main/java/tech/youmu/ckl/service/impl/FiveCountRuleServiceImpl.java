/**
 * @Title: FiveCountRuleServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * 
 * @author yjh
 * @date 2016年9月7日 下午10:56:26
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.domain.FiveCountRule;
import tech.youmu.ckl.mapper.BaseMapper;
import tech.youmu.ckl.mapper.FiveCountRuleMapper;
import tech.youmu.ckl.service.IFiveCountRuleService;

/**
 * 5星好评对应获得的分数
 * @author yjh
 *
 */
@Service
public class FiveCountRuleServiceImpl extends BaseServiceImpl<FiveCountRule> implements IFiveCountRuleService{

	private FiveCountRuleMapper fiveCountRuleMapper;
	
	/**
	 * @param baseMapper
	 */
	@Autowired
	public FiveCountRuleServiceImpl(FiveCountRuleMapper fiveCountRuleMapper) {
		super(fiveCountRuleMapper);
		this.fiveCountRuleMapper = fiveCountRuleMapper;
	}

}
