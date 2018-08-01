package cn.cxnxs.chat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.cxnxs.chat.redis.dao.MessageDAO;
import cn.cxnxs.chat.util.Message;
import cn.cxnxs.chat.websocket.pojo.CommonMessage;

/**
 * @author 蒙锦远
 * @date 2017年12月23日 下午8:03:54
 * @description
 */
@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageDAO messageDAO;

	@ResponseBody
	@RequestMapping("/getPubHistory")
	public Message getPubHistory(HttpServletRequest request) {

		long page = 1;
		long size = 10;
		
		if(request.getParameter("page")!=null){
			page = Long.parseLong(request.getParameter("page"));
		}
		
		if(request.getParameter("size")!=null){
			size = Long.parseLong(request.getParameter("size"));
		} 
		
		long begin=(page-1)*size;
		long end=page*size; 
		String chatroom=request.getParameter("chatroom"); 
		List<CommonMessage> textMessages = messageDAO.getPubMessage(chatroom, begin, end);

		if (textMessages != null) {
			Message message = Message.success();
			message.setContent("获取聊天历史成功");
			message.setArgs(textMessages);
			return message;
		} else {
			Message message = Message.error();
			message.setContent("获取失败");
			return message;
		}
	}
	
	@ResponseBody
	@RequestMapping("/getPriHistory")
	public Message getPriHistory(HttpServletRequest request) {

		long page = 1;
		long size = 10;
		
		if(request.getParameter("page")!=null){
			page = Long.parseLong(request.getParameter("page"));
		}
		
		if(request.getParameter("size")!=null){
			size = Long.parseLong(request.getParameter("size"));
		} 
		String userId1=request.getParameter("userId1");
		String userId2=request.getParameter("userId2");
		
		long begin=(page-1)*size;
		long end=page*size; 
		List<CommonMessage> textMessages = messageDAO.getPriMessage(userId1,userId2, begin, end);

		if (textMessages != null) {
			Message message = Message.success();
			message.setContent("获取聊天历史成功");
			message.setArgs(textMessages);
			return message;
		} else {
			Message message = Message.error();
			message.setContent("获取失败");
			return message;
		}
	}
}
