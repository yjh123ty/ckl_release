package tech.youmu.ckl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tech.youmu.ckl.app.vo.CommissionMonthInfo;
import tech.youmu.ckl.app.vo.CommissionPointMonthInfo;
import tech.youmu.ckl.app.vo.CommissionYearInfo;
import tech.youmu.ckl.domain.CommissionDetail;
import tech.youmu.ckl.domain.CommissionPointDetail;
import tech.youmu.ckl.domain.UserCommission;
import tech.youmu.ckl.domain.UserCommissionItem;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.CommissionQuery;
import tech.youmu.ckl.query.PageList;

public interface CommissionMapper {

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午2:33:55;</p>
     *	<p>Description: 上个月佣金;</p>
     *  @param userId
     *  @param lastMonth
     *  @return
     */
    double getLastMonthTotalCommission(@Param("userId")Long userId,@Param("lastMonth") String lastMonth);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午2:34:08;</p>
     *	<p>Description: 上个月点数;</p>
     *  @param userId
     *  @param lastMonth
     *  @return
     */
    double getLastMonthTotalPoint(@Param("userId")Long userId,@Param("lastMonth") String lastMonth);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午2:34:21;</p>
     *	<p>Description: 年度佣金;</p>
     *  @param userId
     *  @param lastMonth
     *  @return
     */
    double getYearTotalCommission(@Param("userId")Long userId,@Param("year") String year);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午3:59:54;</p>
     *	<p>Description: 佣金点数明细;</p>
     *  @param userId
     *  @return
     */
    List<CommissionPointMonthInfo> findCommissionPointMonthInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午5:05:56;</p>
     *	<p>Description: 佣金明细;</p>
     *  @param userId
     *  @return
     */
    List<CommissionMonthInfo> findCommissionMonthInfo(Long userId);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月10日下午5:27:36;</p>
     *	<p>Description: 年度总佣金;</p>
     *  @param userId
     *  @return
     */
    List<CommissionYearInfo> findCommissionYearInfo(Long userId);

    void saveBatchCommissionPointDetail(@Param("list")List<CommissionPointDetail> commissionPointDetails);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日下午4:11:27;</p>
     *	<p>Description: 总佣金分页;</p>
     *  @param query
     *  @return
     */
    List<UserCommission> findTotalCommissionByQuery(BaseQuery query);
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月17日下午4:11:27;</p>
     *  <p>Description: 总佣金分页条数;</p>
     *  @param query
     *  @return
     */
    long findCountTotalCommissionByQuery(BaseQuery query);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月18日下午2:44:58;</p>
     *	<p>Description: TODO;</p>
     *  @param commissionDetail
     */
    void saveCommissionDetail(CommissionDetail commissionDetail);

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月24日上午9:17:13;</p>
     *	<p>Description: 获取上个月的点数;</p>
     *  @param lastMonthOfToday
     *  @return
     */
    List<CommissionPointDetail> findLastMonthCommissionPointDetail(String lastMonthOfToday);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月18日下午5:05:14;</p>
     *	<p>Description: 查询佣金明细条目数;</p>
     *  @param commissionQuery
     *  @return
     */
    long findCountCommissionDetailByQuery(CommissionQuery commissionQuery);

    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月18日下午5:05:38;</p>
     *	<p>Description: 查询佣金明细;</p>
     *  @param commissionQuery
     *  @return
     */
    List<UserCommissionItem> findCommissionDetailByQuery(CommissionQuery commissionQuery);

    void saveBatchCommissionDetail(@Param("list")List<CommissionDetail> commissionDetails);


    void updateCommissionPointIsBalanceByLastMonth(String lastMonth);

  
}