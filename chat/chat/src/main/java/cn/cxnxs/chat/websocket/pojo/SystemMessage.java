package cn.cxnxs.chat.websocket.pojo;

/**
 * @author 蒙锦远
 * @date 2017年12月22日 下午3:48:43
 * @description
 */
public class SystemMessage extends BaseMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String content;
	private Object args;
	public SystemMessage() {}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getArgs() {
		return args;
	}

	public void setArgs(Object arg) {
		this.args = arg;
	}
	
	
}
