package ThreadForCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 你就是下一个大佬
 * @create 2026/1/3 10:50
 * 	Callable 特点：
 * 		1：有返回值 2：可以抛出异常 3：方法不同 需要重写使用cell()
 */
public class ThreadForCallable {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		MyThread myThread = new MyThread();
		FutureTask<Integer> integerFutureTask = new FutureTask<>(myThread);
		new Thread(integerFutureTask, "A").start();
		/**
		 * get 方法可能会产生阻塞，因为需要获取结果。如果代码执行时间很长。或者使用异步通信
		 */
		Integer integer = integerFutureTask.get();
		System.out.println(integer);
		
	}
}

class MyThread implements Callable<Integer> {
	
	@Override
	public Integer call() throws Exception {
		return 1024;
	}
}

