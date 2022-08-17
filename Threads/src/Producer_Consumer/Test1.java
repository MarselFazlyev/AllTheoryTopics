package Producer_Consumer;

public class Test1 {
    public static void main(String[] args) {
        WaitAndNotify wn = new WaitAndNotify();

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
                wn.consume();
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
            synchronized(this){
                System.out.println("Producer thread started...");
                wait();
                System.out.println("Producer thread resumed!");
        }

        public void consume() {

        }
}



}
