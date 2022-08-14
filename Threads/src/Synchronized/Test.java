package Synchronized;

public class Test {
    private int counter;
    public static void main(String[] args) {
         Test test = new Test();
         test.doWork();

    }

    public synchronized void inkrement(){ // данный метод может выполняться а один момент времени только одним потоком
        counter++;
    }

    // пример synchronized block
 /*   public void inkrement() {
        synchronized (this) {
            counter++;
        }
    } */


    public void doWork() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10000 ; i++) {
                    counter++;
                }
            }
        });
        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i <10000 ; i++) {
                    counter++;
                }
            }
        });

        thread.start();
        thread2.start();

        try {
            thread.join();// поток main ждет окончвния работы потока thread
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      /*  try { // если не дать паузу, потоки не успевают инкрементировать, в консоли counter будет равняться нулю, поток
            // main сразу отрабатывает  строчку  System.out.println(counter);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(counter);

    }
}
