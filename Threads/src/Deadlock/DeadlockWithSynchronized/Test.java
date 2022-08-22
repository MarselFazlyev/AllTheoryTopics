package Deadlock.DeadlockWithSynchronized;

import java.util.Random;

public class Test {
    // Описание примера с Synchronized захватом двух мониторов
    // работает лишь, если захватываются мониторы объектов в одном и том же порядке, в противном случае возникнет
    // deadlock
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            //захват двух мониторов с помощью synchronized
            synchronized (account1) {
                synchronized (account2) {
                    Account.transfer(account1, account2, random.nextInt(100));
                }
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            // если сделать порядок захвата в потоках разным то возникнет deadLock (поток, выполняющий метод thread1,
            // захватит монитор account 21, и зависнет в ожидании монитора account22, так как он будет захвачен потоком,
            // выполняющим метод secondThread, аналогично и другой поток так же зависнет в ожидании монитора account21)
            synchronized (account1) {
                synchronized (account2) {
                    Account.transfer(account2, account1, random.nextInt(100));
                }
            }
        }
    }

    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("Total balance " + (account1.getBalance() + account2.getBalance()));
    }

}

class Account {
    private int balance = 10000;

    public void deposite(int amount) {
        balance += amount;
    }

    public void withDraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account account21, Account account22, int amount) {
        account21.withDraw(amount);
        account22.deposite(amount);

    }
}