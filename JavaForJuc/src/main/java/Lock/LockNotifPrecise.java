package Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 16:51
 * newCondition 的精准唤醒, 要求 A 线程执行完之后 B线程执行 B线程执行完 之后 C 线程执行
 *
 *
 */
public class LockNotifPrecise {
	public static void main(String[] args) {
		
		DataB dataB = new DataB();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				dataB.printA();
			}
		}, "Thread A").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				dataB.printB();
			}
		}, "Thread B").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				dataB.printC();
			}
		}, "Thread C").start();
		
	}
}

class DataB {
	Lock lock = new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();
	private  Integer number = 0;
	
	public void printA(){
		lock.lock();
		try {
			while (number != 0) {
				condition1.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + " : " + number);
			condition2.signal();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
	
	public void printB(){
		lock.lock();
		try {
			while (number != 1) {
				condition2.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + " : " + number);
			condition3.signal();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
	
	public void printC() {
		lock.lock();
		try {
			while (number != 2) {
				condition3.await();
			}
			number = 0;
			System.out.println(Thread.currentThread().getName() + " : " + number);
			condition1.signalAll();
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
}
