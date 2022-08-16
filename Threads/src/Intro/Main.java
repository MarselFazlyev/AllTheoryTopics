package Intro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Создание потока через потомка класса Thread
        MyThread thread1 = new MyThread();
        // вызов потока (вызов метода start(), создает новый поток и запускает в нем переопределенный метод run
        thread1.start();

        // вызов статического метода Thread.sleep, принимает время в миллисекундах, усыпляет поток.
        Thread.sleep(5000);

        //Cоздание потока через передачу в конструктор экземпляра Thread  реализации Runnable
        Thread thread = new Thread(new Runner());
        thread.start();

        // Текущий поток ждет, пока поток thread не закончит свою работу
        thread.join();

        Thread.sleep(5000);

        // создание пула из 3 потоков, передаем в конструктор executorService реализацию интерфейса Runnable
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i <1000 ; i++) {
            //принимаем задачи через метод submit
            executorService.submit(new ExampleForExecotorService(i));
        }
        // потоки начинают выполнение поставленных задач
        executorService.shutdown();
        System.out.println("Все потоки отработали");
        /* текущий поток ждет определенное время (в данном случае - 1 день) выполнения задач пулом потоков и
        продолжает свою работу дальше */
        executorService.awaitTermination(1, TimeUnit.DAYS);


    }
}

// создание класса  потока
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello from Intro.MyThread"+i);
        }
    }
}

//* можно создать поток, переопределив метод run функц. интерфейса Runnable и пердать объект в конструктор потока *//
class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from New Thread" + i);
        }
    }
}

class ExampleForExecotorService implements Runnable {
    private int id;

    public ExampleForExecotorService(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("This is ThreadsPool working on a task %s !!!\n", id);
    }
}






