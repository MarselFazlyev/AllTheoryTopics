package CallableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

public class Test {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Starting");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished");

                Random random = new Random();
                int randomValue = random.nextInt();
                if (randomValue < 5)
                    throw new Exception("Something bad happened");

                return random.nextInt(10);
            }
        });

        executorService.shutdown();
        try {
            // get дожидается окончания выполнения потока , как join
            int result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // getCause позволяет вывести сообщение исключения в главный поток;
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }


    }
}
