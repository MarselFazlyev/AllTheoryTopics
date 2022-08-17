package Producer_Consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //потоеобезопасная очередь

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private static void produce() throws InterruptedException { //заполняем бесконечно очередь
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));// существует похожий метод для очереди  offer, но он потоконебезопасный
        }
    }

    private static void consumer() throws InterruptedException {
        while (true) {
            Random random = new Random();
            Thread.sleep(100);
            if (random.nextInt(10) == 5) {
                System.out.println(queue.take());// потокобезопасный метод для очереди
                System.out.println("Queue size is " + queue.size());
            }
        }
    }

}
