/**
 * Project Name:ckl
 * File Name:AdminTaskWebSocketHandler.java
 * Copyright (c) 成都友木科技 2016
 */
package tech.youmu.ckl.web.websocket;

import java.awt.Image;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import tech.youmu.ckl.utils.UserContext;

/**
 * <p>Title:AdminTaskWebSocketHandler</p>
 * @author zh
 * @version	v1.0
 * <p>Date:2016年10月24日下午2:13:21</p>
 * <p>Description:处理任务socket连接的处理器</p>
 */
@Component
public class AdminTaskWebSocketHandler implements WebSocketHandler {
    
    public static Map<Long, WebSocketSession> sessionMap;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    static {
        sessionMap = new HashMap<Long, WebSocketSession>();
    }

    /**
     * 建立连接后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        logger.info("websocket建立连接..."+userId);
        sessionMap.put(userId, session);
    }
    
    /**
     * 连接关闭
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("websocket连接关闭...");
        //移除缓存的session 根据sessionid相同
        Iterator<Entry<Long, WebSocketSession>> iterator = sessionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Long, WebSocketSession> entry = iterator.next();
            if(entry.getValue().getId().equals(session.getId())){
                sessionMap.remove(entry.getKey());
            }
        }
    }


    /**
     * 前台向后台发送的消息处理
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // nothing todo
    }
    
    

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable t) throws Exception {
        logger.info("websocket传输异常...");
        //1.关闭session
        if(session.isOpen()){
            session.close();
        }
        //2.移除缓存的session 根据sessionid相同
        Iterator<Entry<Long, WebSocketSession>> iterator = sessionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Long, WebSocketSession> entry = iterator.next();
            if(entry.getValue().getId().equals(session.getId())){
                sessionMap.remove(entry.getKey());
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.web.socket.WebSocketHandler#supportsPartialMessages()
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    
    /**
     *  <p>Author:zh;</p>
     *  <p>Date:2016年10月24日下午2:41:14;</p>
     *	<p>Description: 向某一用户发送消息;</p>
     *  @param userId
     *  @param message
     */
    public void sendMessage(Long userId, TextMessage message) {
        WebSocketSession session = sessionMap.get(userId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 给所有在线用户发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(final TextMessage message) {
        Iterator<Entry<Long, WebSocketSession>> it = sessionMap
                .entrySet().iterator();

        // 多线程群发
        while (it.hasNext()) {
            final Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().isOpen()) {
                new Thread(new Runnable() {

                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        }
    }
    
    /**
     * 给所有一群用户发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(List<Long> userIds,  final TextMessage message) {
        Iterator<Entry<Long, WebSocketSession>> it = sessionMap
                .entrySet().iterator();
        // 多线程群发
        while (it.hasNext()) {
            final Entry<Long, WebSocketSession> entry = it.next();
            if (userIds.contains(entry.getKey()) && entry.getValue().isOpen()) {
                new Thread(new Runnable() {

                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }
        }
    }
}
