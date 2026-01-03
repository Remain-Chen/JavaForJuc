package ThreadHelper;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/3 12:06
 */
public class CyclicBarrer {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Thread(() -> {
			System.out.println("线程数量到达 7，输出该方法");
		}));
		
		for (int i = 0; i < 7; i++) {
			new Thread( () ->{
				System.out.println("线程创建完成：" + Thread.currentThread().getName());
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, "thread" + i).start();
		}
	}
}
