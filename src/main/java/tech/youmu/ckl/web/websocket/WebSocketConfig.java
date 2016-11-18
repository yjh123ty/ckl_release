/**
 * Project Name:ckl
 * File Name:WebSocketConfig.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * <p>Title:WebSocketConfig</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月24日下午2:04:04</p>
 * <p>Description:WebSocket配置</p>
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    
    /**
     * 处理websocket连接的处理器
     */
    @Autowired
    private AdminTaskWebSocketHandler adminTaskWebSocketHandler;
    

    /* (non-Javadoc)
     * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer#registerWebSocketHandlers(org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry)
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        
        /**
         * 开启SockJS的支持，声明一个SockJsService，和一个url映射，然后提供一个WebSocketHandler来处理消息。
         * 虽然我们是哟个SockJS我们开发的方式是一样的，但是随着浏览器的不同传输的协议可以是Http Streaming，long polling等。
         */
        registry.addHandler(adminTaskWebSocketHandler, "/newtask.do").addInterceptors(new HandShake()); //提供符合W3C标准的Websocket数据 
        registry.addHandler(adminTaskWebSocketHandler, "/sockjs/newtask.do").addInterceptors(new HandShake()).withSockJS();
    }
    
}
