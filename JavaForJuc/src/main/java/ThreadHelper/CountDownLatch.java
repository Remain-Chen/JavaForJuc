package ThreadHelper;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/3 11:34
 * 	CountDownLatch
 * 		线程减法计数器，主要作用
 * 			1：	CountDownLatch.await() 当线程计数器为 0 的时候才会继续往下执行
 * 			2： CountDownLatch.countDown() 线程计数器 -1
 */
public class CountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		// 创建线程计数器. 当有任务必须要执行任务的时候 再使用
		java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(6);
		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "go out");
				// 线程计数器 数量 -1
				countDownLatch.countDown();
			}, "thread" + i).start();
		}
		// 等待线程计数器变为 0 之后才会继续执行
		countDownLatch.await();
		System.out.println("Over");
	}
}
