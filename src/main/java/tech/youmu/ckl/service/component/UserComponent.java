package tech.youmu.ckl.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.domain.IntegralDetail;
import tech.youmu.ckl.domain.IntegralLevelRule;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.mapper.BadgeMapper;
import tech.youmu.ckl.mapper.IntegralMapper;
import tech.youmu.ckl.mapper.UserMapper;

@Component
public class UserComponent {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private IntegralMapper integralMapper;
    
    @Autowired
    private BadgeMapper badgeMapper;

    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月7日下午1:56:44;</p>
     *	<p>Description: 增加积分;</p>
     */
    public void addIntegral(Long userId, String remark, Integer integral) {
        User user = (User) userMapper.getById(userId);
        if(user == null){
            return;
        }
        user.setIntegral(user.getIntegral()+integral);
        IntegralLevelRule integralLevelRule = integralMapper.getIntegralLevelRule(user.getIntegral());
        user.setLevel(integralLevelRule.getLevel());
        userMapper.update(user);
        integralMapper.save(new IntegralDetail(userId,remark,integral));
    }
    
    
    
    
}
