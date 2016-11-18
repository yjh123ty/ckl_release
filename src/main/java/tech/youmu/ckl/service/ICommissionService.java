package tech.youmu.ckl.service;

import java.util.List;

import tech.youmu.ckl.app.vo.CommissionInfo;
import tech.youmu.ckl.app.vo.CommissionMonthInfo;
import tech.youmu.ckl.app.vo.CommissionPointMonthInfo;
import tech.youmu.ckl.app.vo.CommissionYearInfo;
import tech.youmu.ckl.domain.UserCommission;
import tech.youmu.ckl.domain.UserCommissionItem;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.CommissionQuery;
import tech.youmu.ckl.query.PageList;

/**
 * 
 * <p>Title:ICommissionService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月10日上午11:14:53</p>
 * <p>Description:佣金</p>
 */
public interface ICommissionService {

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午3:58:42;</p>
     *	<p>Description: 佣金信息;</p>
     *  @param userId
     *  @return
     */
    CommissionInfo getCommissionInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午3:58:25;</p>
     *	<p>Description: 点数明细;</p>
     *  @param userId
     *  @return
     */
    List<CommissionPointMonthInfo> findCommissionPointMonthInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午5:05:28;</p>
     *	<p>Description: 佣金明细;</p>
     *  @param userId
     *  @return
     */
    List<CommissionMonthInfo> findCommissionMonthInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午5:27:07;</p>
     *	<p>Description: 年度总佣金;</p>
     *  @param userId
     *  @return
     */
    List<CommissionYearInfo> findCommissionYearInfo(Long userId);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日下午4:10:07;</p>
     *	<p>Description: 用户累计佣金;</p>
     *  @param query
     *  @return
     */
    PageList<UserCommission> findPageTotalCommissionByQuery(BaseQuery query);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月18日下午4:57:01;</p>
     *	<p>Description: 分页查询分销明细;</p>
     *  @param commissionQuery
     *  @return
     */
    PageList<UserCommissionItem> findPageUserCommissionDetail(CommissionQuery commissionQuery);

}
