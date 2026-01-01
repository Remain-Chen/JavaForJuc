package Lock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 15:08
 * 	synchronized 线程通讯 当A线程执行完之后，B线程执行。B线程执行完之后 A 线程执行
 * 		方式1：使用wait() 方法以及 notifyAll() 方法。当A线程执行完之后，调用wait()方法 让线程A进入等待(wait()方法为object类，会释放锁), 然后去执行线程B
 */
public class ThreadWaitAndNotifAll {
	public static void main(String[] args) {
		Data data = new Data();
		new Thread(()-> {
			try {
				for (int i = 0; i < 10; i++) {
					data.addNumber();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread A").start();
		new Thread(()-> {
			try {
				for (int i = 0; i < 10; i++) {
					data.subNumber();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread B").start();
	}
}

class Data {
	private  Integer number = 0;
	
	public synchronized void addNumber() throws InterruptedException {
		while (number != 0) {
			this.wait();
		}
		number++;
		System.out.println(Thread.currentThread().getName() + " : " + number);
		this.notifyAll();
	}
	
	public synchronized void subNumber() throws InterruptedException {
		while (number == 0) {
			this.wait();
		}
		number--;
		System.out.println(Thread.currentThread().getName() + " : " +number);
		this.notifyAll();
	}
}
