
package tech.youmu.ckl.web.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.utils.UserContext;


/**
 * Socket建立连接（握手）和断开
 * 
 * @author Goofy
 * @Date 2015年6月11日 下午2:23:09
 */
public class HandShake implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession session = servletRequest.getServletRequest().getSession(false);
        Employee employee = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        attributes.put("userId", employee.getId());
        return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	}

}