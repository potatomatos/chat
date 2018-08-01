package cn.cxnxs.chat.websocket.util;

import java.util.UUID;

import net.sf.json.JSONObject;

/**
* @author 蒙锦远
* @date 2017年12月23日 下午2:03:04
* @description 
*/
public class WebsocketUtil {
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
      }
	
	public static String objectToJson(Object object){
		return JSONObject.fromObject(object).toString();
	}
}
