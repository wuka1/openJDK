package juc;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    public static class ThreadTask extends Thread {
        public void run() {
            super.run();
            System.out.println("线程资源:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000000000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        // 线程什么时候创建？--execute执行task时创建
        // 线程怎么保活？--
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
//        pool.allowCoreThreadTimeOut(true); //允许核心线程关闭
//        for (int i = 0; i < 15; i++) {  //6个任务创建的线程数、10个任务和15个任务的执行时长对比
//            int index = i;
//            // execute和submit的区别
//            pool.execute(() -> System.out.println("执行:" + index + "--" + Thread.currentThread().getName()));
////            pool.submit(() -> System.out.println(index)); //封装成RunnableFuture，可以获取异常
//        }

        for (int i = 0; i < 16; i++) {
            pool.execute(new ThreadTask());
        }
//        //关闭线程池
//        pool.shutdown();
//        pool.shutdownNow();
    }
}
