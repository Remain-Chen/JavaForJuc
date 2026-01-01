package Lock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 11:07
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

