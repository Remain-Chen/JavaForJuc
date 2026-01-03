package threadSafe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/2 16:59
 */
public class ThreadSet {
	public static void main(String[] args) {
		/**
		 * 		ConcurrentModificationException：HashSet 线程不安全
		 * 			解决方法：
		 * 				1：使用Collections.synchronizedList(new HashSet<>())。使用了synchronized关键字。
		 * 				2: 使用CopyOnWriteArraySet. 使用了synchronized关键字。
		 * 			hashSet底层是什么？ =》》hashmap，hashset.add()方法本质是 hashmap.put()
		 */
		HashSet<String> hashSet = new HashSet<>();
		for (int i = 0; i < 50; i++) {
			new Thread(()->{
				hashSet.add(UUID.randomUUID().toString());
				System.out.println(hashSet);
			}).start();
		}
	}
}
