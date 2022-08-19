package Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        // вызов пула потоков, равного 200 потокам
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        // вызов синглтона СOnnection
        Connection connection = Connection.getConnection();
        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}


// Singleton
class Connection {
    private static Connection connection = new Connection();
    private int connectionCount;
    // лимит одновременных соединений с ресурсом выставляем, например 10
    private Semaphore semaphore = new Semaphore(10);

    // конструктор для синглтона
    private Connection() {

    }


    public static Connection getConnection() {
        return connection;
    }

    // данный метод  демонстрирует ограничение только для 10 потоков выполнения метода work()
    public void work() throws InterruptedException {
        //как только 11 по счету поток придет, семафор будет равным нулю, и поток будет в ожидании исполнения метода и
        //вызова на этом же семафоре метода release()
        semaphore.acquire();
        try {
            doWork();
        } finally {
            // гарантированное освобождение ресурса для выполнения следующим потоком, даже если в методе do work вылетит
            // исключение
            semaphore.release();
        }


    }

    // имитация выполнения рандомной работы сервера
    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
            System.out.println(connectionCount);
        }
        Thread.sleep(5000);

        synchronized (this) {
            connectionCount--;
        }
    }


}
