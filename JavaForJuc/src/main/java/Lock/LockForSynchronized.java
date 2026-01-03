package Lock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 11:07
 * 		synchronized:
 * 			当同一个资源类 开启多线程调用两个被synchronized修饰的方法时： 先调用 A 方法 后调用B方法 最先开始的一定是A方法。因为A 方法会先获得锁
 * 			当同一个资源类 开启多线程调用一个被synchronized修饰的方法 另一个不被 synchronized修饰的方法修饰的方法时。不一定谁先执行。
 * 				没有被synchronized修饰的方法修饰的方法不会等待synchronized修饰的方法先执行
 * 			synchronized 会对当前对象上锁。如果是同一个资源类的多线程，没有得到锁的线程会进行等待。多个new对象，会有多把锁
 * 			如果是static synchronized会对编译后的class文件进行上锁。多个new对象共享同一把锁
 */
public class LockForSynchronized {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		/**
		 * 开辟3个线程 调用ticket类当中的sale() 方法
		 * 	issue 1：线程不会按照顺序执行
		 * 		解决方法：
		 * 			1： 使用关键字 synchronized,让线程顺序执行 进行排队
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
class Ticket {
	private int number = 40;
	
	public synchronized void sale() {
		if (number > 0) {
			number --;
			System.out.println("线程: " + Thread.currentThread().getName() + " 卖出了1张票，" + "还剩下：" +  number);
		}
	}
}

