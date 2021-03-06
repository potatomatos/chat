package cn.cxnxs.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @author 蒙锦远
* @date 2017年12月19日 下午10:19:56
* @description 
*/
@RestController
public class DemoController {

	@ResponseBody
	@RequestMapping(value="/hello",produces = "application/json; charset=utf-8",method=RequestMethod.PUT)
	public String hello(){
		return "你好";
	}
	
	@RequestMapping(value="/hello",produces = "application/json; charset=utf-8",method=RequestMethod.GET)
	public String insert(){
		return "你好";
	}
	
	
}
