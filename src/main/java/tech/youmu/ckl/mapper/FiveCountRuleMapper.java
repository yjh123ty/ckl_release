/**
 * @Title: FiveCountRuleMapper.java
 * @Package tech.youmu.ckl.mapper
 * 
 * @author yjh
 * @date 2016年9月7日 下午10:54:35
 * @version V1.0
 */

package tech.youmu.ckl.mapper;

import tech.youmu.ckl.domain.FiveCountRule;

/**
 * @author yjh
 *
 */
public interface FiveCountRuleMapper extends BaseMapper<FiveCountRule>{
	/**
	 * 根据五星好评数获得分数
	 * @param fiveCount
	 * @return
	 */
	FiveCountRule getPointByFiveCount(Integer fiveCount);
}
