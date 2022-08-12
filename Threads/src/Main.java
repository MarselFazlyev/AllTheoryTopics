public class Main {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        // вызов потока (вызов метода start(),создает новый поток и запускает в нем переопределенный метод run
        thread1.start();
    }
}
// создание потока
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.println("Hello from MyThread");
        }
    }
}
