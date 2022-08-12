public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread1 = new MyThread();
        // вызов потока (вызов метода start(),создает новый поток и запускает в нем переопределенный метод run
        thread1.start();
        // вызов статического метода Thread.sleep,принимает время в миллисекундах,усыпляет поток.
        Thread.sleep(3000);
        //создание потока через пержачу в конструктор реализации Runnable
        Thread thread = new Thread(new Runner());
        thread.start();
    }
}
// создание класса  потока
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.println("Hello from MyThread");
        }
    }
}
//* можно создать поток, переопределив метод run функц. интерфейса Runnable и пердать объект в конструктор потока *//
class Runner implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i <1000 ; i++) {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Hello from New Thread");
        }
    }
}
