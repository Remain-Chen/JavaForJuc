package threadPool;

import java.util.concurrent.*;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/3 17:38
 * 	池化技术：线程可以复用 、可以管理最大并发数、管理线程
 * 		1：降低资源消耗
 * 		2：提高响应速度
 * 		3：方便管理
 */
public class ThreadPoolDemo {
	public static void main(String[] args) {
		/**
		 * 线程池的 3 大方法
		 * 	Executors.newSingleThreadScheduledExecutor();//创建单个线程
		 * 	Executors.newFixedThreadPool(5); // 创建一个固定的线程池大小
		 * 	Executors.newCachedThreadPool(); //可伸缩的 遇强则强 遇弱则弱
		 */
		ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();//单个线程
//		Executors.newFixedThreadPool(5); // 创建一个固定的线程池大小
//		Executors.newCachedThreadPool(); //可伸缩的 遇强则强 遇弱则弱
		/**
		 * 线程池的七大参数
		 *  1: 核心线程数 2：最大线程数 3：线程的存活时间 4：单线程存活时间单位 5：阻塞队列 6：线程的工厂 7：线程的拒绝策略
		 *  	2：线程的4种拒绝策略
		 *  			1：new ThreadPoolExecutor.AbortPolicy() 直接拒绝抛出异常
		 *  			2：new ThreadPoolExecutor.CallerRunsPolicy() 从哪里来的回哪里去
		 *  			3：new ThreadPoolExecutor.DiscardPolicy() 队列满了 不会抛出异常
		 *  			4：new ThreadPoolExecutor.DiscardOldestPolicy() 尝试竞争，也不会抛出异常
		 */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 3,
				TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		try {
			for (int i = 0; i < 10; i++) {
				executorService.execute(() -> {
						System.out.println(Thread.currentThread().getName() + "go out");
				});
			}
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			executorService.shutdown();//释放线程
		}
	}
}
