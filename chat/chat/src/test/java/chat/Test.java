package chat;

import cn.cxnxs.chat.util.Tuling;

/**
* @author 蒙锦远
* @date 2017年12月23日 下午3:34:47
* @description 
*/
public class Test {
	public static void main(String[] args) {
		String content="查询明天11点北京到南宁航班";
		String result=Tuling.TulingRobot(content);
		System.out.println(result);
	}
}
