package tech.youmu.ckl.service.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.CommissionDetail;
import tech.youmu.ckl.domain.CommissionPointDetail;
import tech.youmu.ckl.mapper.CommissionMapper;
import tech.youmu.ckl.mapper.UserBillMapper;
import tech.youmu.ckl.utils.DateUtil;
import tech.youmu.ckl.utils.XingeUtil;

@Component
public class CommissionSettleComponent {

    private static Logger log=LoggerFactory.getLogger(CommissionSettleComponent.class);
    
    @Autowired
    private CommissionMapper commissionMapper;
    
    @Autowired
    private UserBillMapper userBillMapper;
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月28日上午10:31:46;</p>
     *	<p>Description: TODO;</p>
     */
//    @Scheduled(fixedRate = 1000*60*60*24)//24小时执行一次
    @Scheduled(cron="0 0 1 1 * ?")
    public void settleCommission() {
        List<CommissionPointDetail> list= commissionMapper.findLastMonthCommissionPointDetail(DateUtil.getLastMonthOfToday());
        List<CommissionDetail> commissionDetails = new ArrayList<>();
        List<Long> userIds = userBillMapper.findLastMonthMeetExpenseUserId(DateUtil.getLastMonthOfToday());
        for(CommissionPointDetail commissionPointDetail:list){
            if(!userIds.contains(commissionPointDetail.getUserId())){
                continue;
            }
            double totalPointCount = commissionPointDetail.getPointCount();
            double rebateCommission = getRebateCommission(totalPointCount);
            if(rebateCommission>0){
                commissionDetails.add(new CommissionDetail(commissionPointDetail.getUserId(), "折让奖励", commissionPointDetail.getPointCount().toString(), StatusConst.TWO, rebateCommission, StatusConst.TWO));
            }
            double excessCommission = getExcessCommission(totalPointCount);
            if(excessCommission>0){
                commissionDetails.add(new CommissionDetail(commissionPointDetail.getUserId(), "超额奖励", commissionPointDetail.getPointCount().toString(), StatusConst.TWO, rebateCommission, StatusConst.THREE));
            }
        }
        if(commissionDetails.size()>0){
            commissionMapper.saveBatchCommissionDetail(commissionDetails);
        }
        XingeUtil.grantCommissionMessage(userIds);
        commissionMapper.updateCommissionPointIsBalanceByLastMonth(DateUtil.getLastMonthOfToday());
        
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月24日上午10:49:51;</p>
     *	<p>Description: 获取折让佣金;</p>
     *  @return
     */
    private double getRebateCommission(double totalPointCount){
        double rebatePointCount=0;
        if(totalPointCount>10000){
            rebatePointCount = 10000; 
        }
        return rebatePointCount*getRebateDiscount(rebatePointCount);
    }
    
    private double getExcessCommission(double totalPointCount){
        if(totalPointCount<=10000){
            return 0; 
        }
        int excessPointCount=(int)totalPointCount-10000;
        return excessPointCount*getExcessDiscount(excessPointCount);
    }
    
    
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月24日上午9:58:02;</p>
     *	<p>Description: 获取折让比例;</p>
     *  @return
     */
    private double getRebateDiscount(double totalPointCount){
        if(totalPointCount<2000){
            return 0;
        }
        if(totalPointCount<4000){
            return 0.02;
        }
        if(totalPointCount<6000){
            return 0.04;
        }
        if(totalPointCount<8000){
            return 0.06;
        }
        if(totalPointCount<10000){
            return 0.08;
        }else{
            return 0.1;
        }
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月24日上午10:05:00;</p>
     *	<p>Description: 获取超额百分比;</p>
     *  @param totalPointCount
     *  @return
     */
    private static double getExcessDiscount(int excessPointCount){
        log.info("excessPoint:"+excessPointCount+";");
        if(excessPointCount>500000){
            return 0.1;
        }
        double discount = excessPointCount/20000*0.25+3.75;
        return discount/100;
    }
    
    
}
