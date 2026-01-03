package Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/3 12:31
 */
public class RedWriteLockDemo {
	public static void main(String[] args) {
		/**
		 * 没有使用读写锁的情况下，即使加上了lock 锁或者使用了 synchronized 关键字，读写也会混乱
		 */
//		MyCache myCache = new MyCache();
//		for (int i = 0; i < 5; i++) {
//			final String temp = i + "  ";
//			new Thread(() -> {
//				myCache.put(temp, "");
//			}, "Thread" + i).start();
//		}
//
//		for (int i = 0; i < 5; i++) {
//			final String temp = i + "  ";
//			new Thread(() -> {
//				myCache.get(temp);
//			}, "Thread" + i).start();
//		}
		/**
		 * 使用读写锁 会先写后读，等写入完全之后再开始读取数据
		 */
		MyCacherReadWriteLock myCacherReadWriteLock = new MyCacherReadWriteLock();
		for (int i = 0; i < 5; i++) {
			final String temp = i + "  ";
			new Thread(() -> {
				myCacherReadWriteLock.put(temp, "");
			}, "Thread" + i).start();
		}
		
		for (int i = 0; i < 5; i++) {
			final String temp = i + "  ";
			new Thread(() -> {
				myCacherReadWriteLock.get(temp);
			}, "Thread" + i).start();
		}
	}
}

/**
 * 自定义缓存
 */
class MyCache {
	private volatile Map<String, String> map = new HashMap<>();
	Lock lock = new ReentrantLock();
	
	// 存数据：写数据的过程
	public void put(String key, String value ) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 数据开始写入");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + " 数据写入OK");
		} catch (Exception e){
		
		}finally {
			lock.unlock();
		}
	}
	
	// 取：读数据的过程
	public void  get(String key) {
		lock.lock();
		try {
		System.out.println(Thread.currentThread().getName() + " 数据开始取出");
		map.get(key);
		System.out.println(Thread.currentThread().getName() + " 数据取出OK");
		} catch (Exception e){
		
		}finally {
			lock.unlock();
		}
	}
}

/**
 * 自定义缓存
 */
class MyCacherReadWriteLock {
	private volatile Map<String, String> map = new HashMap<>();
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	// 存数据：写数据的过程
	public void put(String key, String value ) {
		readWriteLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 数据开始写入");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + " 数据写入OK");
		} catch (Exception e){
		
		}finally {
			readWriteLock.writeLock().unlock();
		}
	}
	
	// 取：读数据的过程
	public void  get(String key) {
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 数据开始取出");
			map.get(key);
			System.out.println(Thread.currentThread().getName() + " 数据取出OK");
		} catch (Exception e){
		
		}finally {
			readWriteLock.readLock().unlock();
		}
	}
}

