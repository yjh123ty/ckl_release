package tech.youmu.ckl.service.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.mapper.OrderMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.utils.OrderCodeUtil;
import tech.youmu.ckl.utils.UserCodeUtil;

@Component
public class CodeComponent implements InitializingBean{

    private static Logger log=LoggerFactory.getLogger(CodeComponent.class);
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("CodeComponent...");
        createUserCode();
        createOrderCode();
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月18日上午10:31:34;</p>
     *	<p>Description: 邀请码;</p>
     */
    private void createUserCode(){
        List<String> codes = userMapper.findCode();
        UserCodeUtil.createUserCodeSet(codes);
    }
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月18日上午10:31:48;</p>
     *	<p>Description: 订单使用码;</p>
     */
    private void createOrderCode(){
        List<String> codes = orderMapper.findCode();
        OrderCodeUtil.createOrderCodeSet(codes);
       
    }
    

    
    
}
