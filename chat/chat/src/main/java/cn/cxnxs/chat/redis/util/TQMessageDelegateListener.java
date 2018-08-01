package cn.cxnxs.chat.redis.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONObject;

import cn.cxnxs.chat.websocket.ChatHandler;
import cn.cxnxs.chat.websocket.pojo.Client;
import cn.cxnxs.chat.websocket.pojo.CommonMessage;
import cn.cxnxs.chat.websocket.pojo.MessageType;
import cn.cxnxs.chat.websocket.util.WebsocketUtil;

public class TQMessageDelegateListener {
	
	@Autowired
	private ChatHandler chatHandler; 
	public void handleMessage(String message) {
		System.err.println(chatHandler);
		System.err.println(ChatHandler.onlineUsers);
		System.out.println("监听到的消息为：" + message);
		
	}
}