package cn.cxnxs.chat.util;

public class Message {
	private String type;
	private String content;
	private Object args;
	public Message(String type){
		this.type = type;
	}
	public Message(String type, String content){
		this.type = type;
		this.content = content;
	}
	public static Message error(){
		return new Message("error");
	}
	public static Message error(String content){
		return new Message("error",content);
	}
	public static Message success(){
		return new Message("success");
	}
	public static Message success(String content){
		return new Message("success", content);
	}
	public static Message needLogin() {
		return new Message("needLogin");
	}
	public static Message needLogin(String content) {
		return new Message("needLogin",content);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object getArgs() {
		return args;
	}
	public void setArgs(Object args) {
		this.args = args;
	}
	
}
