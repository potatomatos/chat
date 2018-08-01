package cn.cxnxs.chat.websocket.pojo;

import java.io.Serializable;

/**
* @author 蒙锦远
* @date 2017年12月22日 下午3:36:35
* @description 
*/
public class BaseMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long createTime;
	private String msgType;
	private String msgId;
	public BaseMessage(){}
	public BaseMessage(String msgType) {
		this.msgType=msgType;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long string) {
		this.createTime = string;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String string) {
		this.msgId = string;
	}
}
