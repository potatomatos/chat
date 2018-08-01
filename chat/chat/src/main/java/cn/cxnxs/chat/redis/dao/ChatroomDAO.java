package cn.cxnxs.chat.redis.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import cn.cxnxs.chat.redis.util.SerializeUtil;
import cn.cxnxs.chat.websocket.pojo.Client;

/**
 * @author 蒙锦远
 * @date 2017年12月19日 下午6:31:42
 * @description
 */
@Component
public class ChatroomDAO {

	final private Logger logger = Logger.getLogger(ChatroomDAO.class);
	@Resource
	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	private final String KEY="users.online.";

	/**
	 * 在线列表添加用户
	 * 
	 * @param client
	 */
	public void addMermber(Client client) {

		String roomKey = client.getChatroomId();
		String userKey = client.getUser().getUserId();
		logger.debug("roomKey："+roomKey);
		logger.debug("userKey："+userKey);
		

		logger.debug("开始保存用户信息");
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					// 在线列表更新
					connection.hSet(redisTemplate.getStringSerializer().serialize(KEY+roomKey),
							SerializeUtil.objectToBytes(userKey),
							SerializeUtil.objectToBytes(client));
				} catch (SerializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
		logger.debug("用户信息已保存");
	}

	/**
	 * 删除在线用户
	 * 
	 * @param chatroomId
	 * @param userId
	 */
	public Boolean delMermber(String chatroomId, String userId) {

		logger.debug("开始从聊天室[" + chatroomId + "]删除用户[" + userId + "]");
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				try {
					return connection.hDel(redisTemplate.getStringSerializer().serialize(KEY+chatroomId),
							SerializeUtil.objectToBytes(userId));
					
				} catch (SerializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				
			}
		});
	}

	/**
	 * 获取在线列表
	 * 
	 */
	public Map<String, Client> getOnlineMermbers(String roomId) {
		logger.debug("开始获取在线用户");
		Map<String, Client> clientsMap = new HashMap<>();
		return redisTemplate.execute(new RedisCallback<Map<String, Client>>() {
			public Map<String, Client> doInRedis(RedisConnection connection) throws DataAccessException {

				Map<byte[], byte[]> resultMap = connection
						.hGetAll(redisTemplate.getStringSerializer().serialize(KEY+roomId));

				for (Map.Entry<byte[], byte[]> entry : resultMap.entrySet()) {
					try {
						String key = (String) SerializeUtil.bytesToObject(entry.getKey());
						Client value = (Client) SerializeUtil.bytesToObject(entry.getValue());
						clientsMap.put(key, value);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				logger.debug("在线列表获取完成");
				return clientsMap;
			}
		});
	}

	public void publish(String channel,final String message) throws Exception{
		logger.debug("开始发布消息");
		
		redisTemplate.convertAndSend(channel, message);
		logger.debug("消息发布完毕");
	}
	
}
