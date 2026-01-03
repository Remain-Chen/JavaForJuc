package threadSafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/2 15:53
 */
public class ThreadArrrayList {
	public static void main(String[] args) {
		/**
		 * java.util.ConcurrentModificationException：
		 * 		集合内部维护了一个 modCount（修改计数器），每次对集合进行结构性修改（改变大小）时，modCount 就会增加。迭代器创建时会记录当前的 modCount 值（称为 expectedModCount）。
		 * 		在迭代过程中，每次操作前都会检查这两个值是否一致，不一致就抛出异常。
		 * 			ArrayList 本身非线程安全的数据结构，底层为动态数组结构。初始大小为 10 ，每次扩容为原来大小的1.5倍。有序 查询快，删除修改数据慢。原因在于每次删除修改数据时候需要移动其他index的数据
		 * 			linkedList 非线程安全的数据结构，底层为双向链表结构。查询慢，插入数据快。存储相同的数据时需要比arraylist更多的空间，因为需要记录双向指针。linkedList的空间利用率更好，不会浪费太多空间
		 * 		ConcurrentModificationException：
		 * 			解决方法：
		 * 				1：使用vector。使用了synchronized关键字，线程安全。jak 1.0引入 不推荐	new ArrayList<>() =》new Vector<>()
		 * 				2：使用Collections.synchronizedList(new ArrayList<>())。使用了synchronized关键字。
		 * 				3: 使用CopyOnWriteArrayList. 使用了synchronized关键字。使用
		 */
		List<String> stringList = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			new Thread(()->{
				stringList.add(UUID.randomUUID().toString());
				System.out.println(stringList);
			}).start();
		}
	}
}
