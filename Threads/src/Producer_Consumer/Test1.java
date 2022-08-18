package Producer_Consumer;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        ProducerConsumer wn = new ProducerConsumer();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread started...");
            wait();// 1- отдаем монитор (intrinsic lock, 2 - ждем пока будет вызван метод notify().
            System.out.println("Producer thread resumed!");
        }
    }
    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Waiting for return key pressed...");
            scanner.nextLine();
            notify(); // пробуждает поток, который вызывал метод wait(), к дальнейшему выполнению, отдает ему монитор
        }
    }


}
