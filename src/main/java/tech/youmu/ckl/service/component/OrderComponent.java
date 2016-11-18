package tech.youmu.ckl.service.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.HotelRoom;
import tech.youmu.ckl.domain.HotelRoomRecord;
import tech.youmu.ckl.domain.IntegralDetail;
import tech.youmu.ckl.domain.IntegralLevelRule;
import tech.youmu.ckl.domain.Order;
import tech.youmu.ckl.domain.ServiceEvaluation;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.mapper.BadgeMapper;
import tech.youmu.ckl.mapper.IntegralMapper;
import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.ServiceEvaluationMapper;
import tech.youmu.ckl.utils.DateUtil;

@Component
public class OrderComponent {

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private ServiceEvaluationMapper serviceEvaluationMapper;
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年10月28日上午10:52:49;</p>
     *	<p>Description: 取消过期订单;</p>
     */
    @Scheduled(cron="0 0/30 * * * ?")
//    @Scheduled(fixedRate = 1000*60*60*24)
    public void cancelOverdueOrder(){
        List<Order> orders = orderMapper.findOrderByStatus(StatusConst.FOUR);
        List<Long> cancelOrderIds = new ArrayList<>(); 
        for(Order order : orders){
            long differTime = DateUtil.getDifferTime(order.getCreateTime(), DateUtil.getDate());
            if(differTime>24*60*60*1000){
                cancelOrderIds.add(order.getId());
            }
        }
        if(cancelOrderIds.size()>0){
            orderMapper.batchCanelOverdueOrder(cancelOrderIds);
        }
    }
    
    /**
     * 
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年11月16日下午1:55:30;</p>
     *	<p>Description: 设置默认评价，每隔半小时查询一次，三天未评价订单，默认评价为三星;</p>
     */
//    @Scheduled(cron="0 0/30 * * * ?")
    @Scheduled(cron="30 44 14 * * ?")
  public void setDefaultEvaluationOrder(){
      List<Order> orders = orderMapper.findOrderByStatus(StatusConst.SEVEN);
      List<Long> setOrderIds = new ArrayList<>(); 
      for(Order order : orders){
          long differTime = DateUtil.getDifferTime(order.getModifyTime(), DateUtil.getDate());
          if(differTime>3*24*60*60*1000){
              setOrderIds.add(order.getId());
              ServiceEvaluation serviceEvaluation = new ServiceEvaluation();
              serviceEvaluation.setContent("系统默认评分");
              serviceEvaluation.setOrder(order);
              serviceEvaluation.setOrderId(order.getId());
              serviceEvaluation.setStar(3.0D);
              serviceEvaluationMapper.save(serviceEvaluation);
          }
      }
      if(setOrderIds.size()>0){
          orderMapper.batchSetDefaultEvaluationOrder(setOrderIds);
      }
  }
    
}
