package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 11:43
 */
public class LockForLock {
	public static void main(String[] args) {
		Ticket1 ticket = new Ticket1();
		/**
		 * 开辟3个线程 调用ticket类当中的sale() 方法
		 * 	issue 1：线程不会按照顺序执行
		 * 		解决方法：
		 * 			1： 使用关键字lock,让线程顺序执行 进行排队
		 * 				特性：可以传递参数，设置lock是否为公平锁 或者非公平锁
		 * 					跟 synchronized 的区别
		 * 						1：	synchronized是Java当中的关键字，lock 是java当中的类
		 * 						2： synchronized无法判断获取锁的状态，lock 可以判断是否获取到了锁
		 * 						3： synchronized会自动释放锁 ，lock 必须手动释放，如果不释放锁 将会发生死锁现象
		 * 						4： synchronized 线程 1获取锁后如果发生了阻塞 线程2 会一直等待下去，lock 可以强制获取锁 不一定会等待下去
		 * 						5： synchronized 可重入锁，不可以中断，非公平锁，lock 可重入锁。可以判断锁 可以自己设置公平或者非公平
		 * 						6： synchronized 适合锁少量的代码同步问题，lock 适合锁大量代码块
		 */
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				ticket.sale();
			}
		}, "thread A ").start();
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				ticket.sale();
			}
		}, "thread B ").start();
		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				ticket.sale();
			}
		}, "thread C ").start();
	}
}

/**
 * 资源类 oop 只包含属性和方法
 */
class Ticket1 {
	private int number = 40;
	Lock lock = new ReentrantLock();
	
	public void sale() {
		lock.lock();
		try {
			if (number > 0) {
				number --;
				System.out.println("线程: " + Thread.currentThread().getName() + " 卖出了1张票，" + "还剩下：" +  number);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();
		}
	}
}
