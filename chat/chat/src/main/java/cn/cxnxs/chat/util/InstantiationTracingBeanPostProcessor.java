package cn.cxnxs.chat.util;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;

import cn.cxnxs.chat.redis.dao.ChatroomDAO;
/**
 * spring容器初始化操作
 * @author db2admin
 *
 */

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> 
{  
	final private Logger logger = Logger.getLogger(ChatroomDAO.class);
	@Resource
	protected RedisTemplate<String, String> redisTemplate;
	private final String KEY = "users.online.*";
	@Override  
	public void onApplicationEvent(ContextRefreshedEvent event) 
	{  
	    if(event.getApplicationContext().getParent() == null)//root applicationContext没有parent，保证是统一的context
	    { 
	    	Set<String> keys = redisTemplate.keys(KEY);
	    	
	    	logger.debug("keys:"+keys);
	    
			logger.info("spring开始初始化...");
			logger.info("清空在线列表");
			if(keys.size()>0){
				redisTemplate.delete(keys);
				logger.info("在线列表已清空");
			}else{
				logger.info("在线列表无数据");
			}
			
			
	    }  
	} 
}