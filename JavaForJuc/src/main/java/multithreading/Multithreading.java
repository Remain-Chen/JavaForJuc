package multithreading;

import java.util.concurrent.Callable;

import static multithreading.Multithreading.integer;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/1 10:03
 *  传统的多线程写法
 *  	1：继承thread 类
 *  	2：实现runable接口
 *  	3：实现callable接口
 *  		java自己并不能开辟线程是通过c++来开辟线程的
 *  		wait()跟sleep() 方法的区别
 *  			1：wait()只能用于同步代码块，会释放锁，需要唤醒 属于object类
 *  			2：sleep() 不会释放锁，可以设置睡眠时间，属于thread类，会抛出异常需要处理
 *  				并发与并行的区别
 *  					并发：多个方法操作一个对象
 *  					并行：多个方法一起执行 类似于多个人并排走
 *  				多线程的六种状态：新生 运行 阻塞 等待 超时等待 终止
 */
public class Multithreading extends Thread{
	
	static Integer integer = 10;
	
	public static void main(String[] args) {
		MultithreadingForThread multithreadingForThread1 = new MultithreadingForThread();
		Thread thread1 = new Thread(multithreadingForThread1, "thread1");
		Thread thread2 = new Thread(multithreadingForThread1, "thread2");
		Thread thread3 = new Thread(multithreadingForThread1, "thread3");
		/**
		 * start()调用start方法启动线程
		 */
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

/**
 * 多线程实现方法 1：继承thread 类 不能够定义返回值
 * 	重写run方法
 */
class MultithreadingForThread extends Thread {
	@Override
	public void run() {
			while (integer>0) {
				for ( int i = 0 ; i <= 10; i++) {
					System.out.println(Thread.currentThread().getName() +" integer的值为：" + integer--);
				}
			}
	}
}

/**
 * 多线程实现方法 2：实现runnable 接口，不能够定义返回值
 */
class MultithreadingForRunnable implements Runnable {
	
	@Override
	public void run() {
		while (integer>0) {
			for ( int i = 0 ; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() +"integer的值为：" + integer--);
			}
		}
	}
	
}

/**
 * 多线程实现方法 3：Callable 接口, 可以自己定义返回值
 */
class MultithreadingForCallable implements Callable {
	
	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "线程执行完成";
	}
	
}






