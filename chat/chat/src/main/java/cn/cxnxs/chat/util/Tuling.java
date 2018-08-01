package cn.cxnxs.chat.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Tuling {
      
	public static String TulingRobot(String content){
		     StringBuffer sb = new StringBuffer(); 
			
		     
		   try { 
		     String APIKEY = "a24a1b2d25d1bc36f245483570a567f0"; 
    	     String INFO;
    	     INFO = URLEncoder.encode(content, "utf-8");
			
    	     
    	     
				String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
	    	    URL getUrl = new URL(getURL); 
	    	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	    	    connection.connect(); 
	      
	     
	    	    // 取得输入流，并使用Reader读取 
	    	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
	    	    
	    	    
	    	    String line = ""; 
	    	     
	    	    while ((line = reader.readLine()) != null) { 
	    	        sb.append(line); 
	    	    } 
	    	
	    	    reader.close(); 
	    	    // 断开连接 
	    	    connection.disconnect(); 
				
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			
			
			return sb.toString(); 
      }
	
}
