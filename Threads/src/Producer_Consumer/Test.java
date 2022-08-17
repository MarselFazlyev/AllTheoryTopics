package Producer_Consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
   private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //потоеобезопасная очередь
    public static void main(String[] args) {

    }
    private static void produce() { //заполняем бесконечно очередь
        Random random = new Random();
        while (true) {
            queue.offer(random.nextInt());
        }
    }

    private static void consumer(){
        while (true) {
            try {
                queue.take()
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

}
