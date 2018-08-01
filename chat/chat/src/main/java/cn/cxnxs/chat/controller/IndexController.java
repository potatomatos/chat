package cn.cxnxs.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 蒙锦远
* @date 2017年12月20日 下午3:47:49
* @description 主页
*/
@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
