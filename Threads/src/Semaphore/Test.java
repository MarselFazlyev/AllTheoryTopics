package Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        Work work = new Work(semaphore);
        Thread thread1 = new Thread(work);
        Thread thread2 = new Thread(work);
        Thread thread3 = new Thread(work);
        Thread thread4 = new Thread(work);
        Thread thread5 = new Thread(work);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();

        System.out.println(work.getList().size());


    }
}

class Work implements Runnable {
    private static List<Integer> list = new ArrayList<>();
    private Semaphore semaphore;
    private Lock lock = new ReentrantLock();


    public Work(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public List<Integer> getList() {
        return list;
    }

    private void fillList() {
        Random random = new Random();
        lock.lock();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt(10));
        }
        lock.unlock();
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            semaphore.acquire();
            System.out.println(semaphore.availablePermits());
            fillList();
            Thread.sleep(1000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
