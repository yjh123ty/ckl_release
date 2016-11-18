package tech.youmu.ckl.service.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.constants.BadgeConst;
import tech.youmu.ckl.mapper.BadgeMapper;

@Component
public class BadgeComponent{


    private static Logger log=LoggerFactory.getLogger(BadgeComponent.class);
    
    @Autowired
    private BadgeMapper badgeMapper;
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月8日上午11:13:48;</p>
     *  <p>Description: 添加绑定汽车徽章;</p>
     *  @param userId
     *  @param badgeId
     */
    public void carBadge(Long userId){
        addUserBadge(userId, BadgeConst.CAR_BADGE_ID);
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年11月1日下午3:25:32;</p>
     *	<p>Description: 坚持发帖;</p>
     *  @param userId
     *  @param badgeId
     */
    public void topicBadge(Long userId){
        int count = badgeMapper.getTopicCount(userId);
        if(count>BadgeConst.TOPIC_COUNT){
            addUserBadge(userId, BadgeConst.TOPIC_BADGE_ID);
        }
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年11月1日下午3:45:14;</p>
     *	<p>Description: 开始出发;</p>
     *  @param userId
     */
    public void routeBadge(Long userId){
        addUserBadge(userId, BadgeConst.ROUTE_BADGE_ID);
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年11月1日下午4:02:41;</p>
     *	<p>Description: 消费;</p>
     *  @param userId
     */
    public void consumeBadge(Long userId){
        double amount = badgeMapper.getConsumeAmount(userId);
        if(amount>BadgeConst.CONSUME_10000){
            addUserBadge(userId, BadgeConst.CONSUME_10000_BADGE_ID);
        }else if(amount>BadgeConst.CONSUME_5000){
            addUserBadge(userId, BadgeConst.CONSUME_5000_BADGE_ID);
        }else if(amount>BadgeConst.CONSUME_2000){
            addUserBadge(userId, BadgeConst.CONSUME_2000_BADGE_ID);
        }
    }
    
    public void orderBadge(Long userId){
        int count = badgeMapper.getOrderCount(userId);
        if(count>BadgeConst.ORDER_COUNT){
            addUserBadge(userId, BadgeConst.ORDER_BADGE_ID);
        }
    }
    
    private void addUserBadge(Long userId,Long badgeId){
        if(!badgeMapper.isExistUserBadge(userId,badgeId)){
            badgeMapper.saveUserBadge(userId,badgeId);
        }
    }
    
}
