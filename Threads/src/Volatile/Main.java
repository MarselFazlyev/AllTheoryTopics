package Volatile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
// заводим сканер, передаем ему пустую строку, дальше при выполнении потока main срабатывает метод shutdown,
// поток thread прекращает свою работу.
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        thread.shutdown();
    }
}

class MyThread extends Thread {
    // заводим поле running для того,чтобы в любой момент остановить поток (с помощью метода shutdown())
    // для синхронности потоков при использовании значения переменной running добавим volatile
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
