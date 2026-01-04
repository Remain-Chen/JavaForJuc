package Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/4 20:25
 *  Java 关键字 Volatile
 *  	保持数据的可见性
 *  		1： 为什么要保持数据的可见性。因为在多线程的情况下，线程会从主线程copy数据，
 *  			线程没有执行完之前一直使用的是主线程的副本数据。如果其他线程改变了这个数据，对当前线程来说是不可见的
 *  		2： 不保证原子性。
 *  			不加锁，想保证原子性，可以使用原子类
 */
public class VolatileDemo {
	/**
	 * volatile 如果不加， 另外一个线程感知不到num的变化
	 */
	static volatile Integer num = 0;
	public static void main(String[] args) {
		new Thread(() -> {
			while ( num == 0){
//				System.out.println(num);
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		num = 1;
		System.out.println("1");
	}
}
