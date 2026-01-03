package ThreadHelper;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/3 12:23
 */
public class Semaphore {
	public static void main(String[] args) {
		// 信号量=>设置3个信号量，当信号量被占用完之后，线程将会进入等待. 可以作用于并发限流
		java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(3);
		for (int i = 0; i < 6; i++) {
			new Thread(()->{
				try {
					semaphore.acquire(); //占用一个信号量
					System.out.println(Thread.currentThread().getName() + "占到了信号量");
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();// 释放一个信号量
				}
			}, "thread" + i).start();
		}
	}
}
