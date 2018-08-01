package cn.cxnxs.chat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.cxnxs.chat.util.Message;
import cn.cxnxs.chat.websocket.pojo.User;
import cn.cxnxs.chat.websocket.util.MessageConstant;


/**
* @author 蒙锦远
* @date 2017年12月21日 下午10:35:12
* @description 用户请求
*/
@RestController
@RequestMapping("/user")
public class UserController {
	
	@ResponseBody
	@RequestMapping("/login")
	public Message login(HttpServletRequest request) {
		String nickname=request.getParameter("nickname");
		System.err.println(nickname);
		if(nickname!=null){
			HttpSession session=request.getSession();
			session.setMaxInactiveInterval(-1);
			User user=new User();
			user.setNickname(nickname);
			user.setUserId(session.getId());
			
			
			session.setAttribute(MessageConstant.USER, user);
			
			Message message=Message.success();
			message.setArgs(user);
			message.setContent("登陆成功");
			return message;
		}else{
			Message message=Message.error();
			message.setContent("登录失败");
			return message;
		}
		
	}
	
}
