package cn.cxnxs.chat.redis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import cn.cxnxs.chat.redis.util.SerializeUtil;
import cn.cxnxs.chat.websocket.pojo.CommonMessage;

/**
 * @author 蒙锦远
 * @date 2017年12月19日 下午6:31:42
 * @description 对消息队列进行操作
 */
@Component
public class MessageDAO {

	final private Logger logger=Logger.getLogger(MessageDAO.class);
	@Resource
	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
	private final String KEY = "msg.storage.";

	/**
	 * 保存群聊消息
	 * @param message
	 */
	public void savePubMessage(String chatroomId,final CommonMessage message) {
		logger.debug("开始保存群聊消息");
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				String key=KEY+"public."+chatroomId;
				try {
					connection.lPush(redisTemplate.getStringSerializer().serialize(key),
							SerializeUtil.objectToBytes(message));
				} catch (SerializationException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		logger.debug("群聊消息保存完毕");
	}
	/**
	 * 保存私聊消息
	 * @param message
	 */
	public void savePriMessage(final CommonMessage message) {
		String userId1=message.getFromUserId();
		String userId2=message.getToUserId();
		
		logger.debug("开始保存私聊消息");
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				String key1=KEY+"private."+userId1+"."+userId2;
				String key2=KEY+"private."+userId2+"."+userId1;
				
				try {
					connection.lPush(redisTemplate.getStringSerializer().serialize(key1),
							SerializeUtil.objectToBytes(message));
					connection.lPush(redisTemplate.getStringSerializer().serialize(key2),
							SerializeUtil.objectToBytes(message));
					
				} catch (SerializationException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		logger.debug("私聊消息保存完毕");
	}

	/**
	 * 获取私聊聊天记录
	 * @param page
	 * @param size
	 * @return
	 */
	public List<CommonMessage> getPriMessage(String userId1,String userId2,final long index, final long size) {
		
		return redisTemplate.execute(new RedisCallback<List<CommonMessage>>() {
			public List<CommonMessage> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(KEY+"private."+userId1+"."+userId2);
				
				if (connection.exists(key)) {
					List<byte[]> values = connection.lRange(key, index, size);
					// 将byte转成数组
					List<CommonMessage> messages = new ArrayList<CommonMessage>();
					try {
						for (byte[] value : values) {
							CommonMessage message = (CommonMessage) SerializeUtil.bytesToObject(value);
							messages.add(message);
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return messages;
				}
				return null;

			}
		});
	}
	
	/**
	 * 获取群聊聊天记录
	 * @param page
	 * @param size
	 * @return
	 */
	public List<CommonMessage> getPubMessage(String chatroomId,final long index, final long size) {
		
		return redisTemplate.execute(new RedisCallback<List<CommonMessage>>() {
			public List<CommonMessage> doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(KEY+"public."+chatroomId);
				if (connection.exists(key)) {
					List<byte[]> values = connection.lRange(key, index, size);
					// 将byte转成数组
					List<CommonMessage> messages = new ArrayList<CommonMessage>();
					try {
						for (byte[] value : values) {
							CommonMessage message = (CommonMessage) SerializeUtil.bytesToObject(value);
							messages.add(message);
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return messages;
				}
				return null;

			}
		});
	}
}
