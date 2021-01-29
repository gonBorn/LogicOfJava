package threadDemo;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketWindowRunnable implements Runnable{
    private AtomicInteger index = new AtomicInteger(1);
//    private int index = 0;

    private final static int MAX = 50;

    private final static Object MUTEX = new Object();

    @Override
    public void run() {
//        synchronized (MUTEX) {
//            while (index <= MAX) {
//                System.out.println(Thread.currentThread().getName() + " " + index++ );
//            }
//        }
        while (index.get() <= MAX) {
            System.out.println(Thread.currentThread().getName() + " " + index.getAndIncrement() );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final TicketWindowRunnable runnable = new TicketWindowRunnable();
        final Thread thread1 = new Thread(runnable, "number 1");
        final Thread thread2 = new Thread(runnable, "number 2");
        final Thread thread3 = new Thread(runnable, "number 3");
        final Thread thread4 = new Thread(runnable, "number 4");
        Arrays.asList(thread1, thread2, thread3, thread4).forEach(Thread::start);
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    }
}
