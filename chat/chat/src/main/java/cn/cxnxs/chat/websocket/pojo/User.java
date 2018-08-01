package cn.cxnxs.chat.websocket.pojo;

import java.io.Serializable;

/**
 * @author 蒙锦远
 * @date 2017年12月23日 下午1:57:10
 * @description
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String nickname;


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
