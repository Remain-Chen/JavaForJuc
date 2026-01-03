package threadSafe;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/2 17:19
 */
public class ThreadHashMap {
	public static void main(String[] args) {
		/**
		 * 		ConcurrentModificationException：HashMap 线程不安全
		 * 			解决方法：
		 * 				1：使用Collections.synchronizedSortedMap()。使用了synchronized关键字。
		 * 				2: 使用ConcurrentHashMap
		 */
		
		Map<String, String> hashSet = new HashMap<>();
		for (int i = 0; i < 50; i++) {
			new Thread(()->{
				hashSet.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
			}).start();
		}
	}
}
