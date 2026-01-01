package Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 15:52
 * 	newCondition 线程通讯 当A线程执行完之后，B线程执行。B线程执行完之后 A 线程执行
 * 		方式1：使用await() 方法以及 signalAll () 方法。当A线程执行完之后，调用await()方法 让线程A进入等待, 然后去执行线程B
 */
public class LockAwaitAndSignal {
	public static void main(String[] args) {
		DataA data = new DataA();
		new Thread(()-> {
			for (int i = 0; i < 10; i++) {
				data.addNumber();
			}
		}, "thread A").start();
		new Thread(()-> {
			for (int i = 0; i < 10; i++) {
				data.subNumber();
			}

		}, "thread B").start();
	}
}

class DataA {
	private  Integer number = 0;
	
	Lock lock = new ReentrantLock();
	/**
	 * 可以精准的通知唤醒线程
	 */
	Condition condition = lock.newCondition();
	
	public void addNumber() {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + " : " + number);
			condition.signalAll();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
	
	public void subNumber() {
		
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + " : " +number);
			condition.signalAll();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
}
