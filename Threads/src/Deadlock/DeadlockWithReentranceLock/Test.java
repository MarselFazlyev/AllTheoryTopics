package Deadlock.DeadlockWithReentranceLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    // Описание примера с  захватом двух мониторов с помощью ReentranceLock
    // работает  если захватываются мониторы объектов в любом  порядке,надо прописать захват двух мониторов в отдельном методе, используя метод
    // tryLock()
    public static void main(String[] args) throws InterruptedException {
        Runner runner3 = new Runner();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner3.firstThread();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner3.secondThread();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner3.finished();
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    //метод для захвата обоих мониторов аккаунтов 1 и 2 в произвольном порядке с помощью метода  tryLock().
    private void takeLocks(Lock lock1, Lock lock2) {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        // бесконечные попытки захватить оба монитора
        while (true) {
            try {
                // пытаемся захватить оба монитора;
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                // если удалось захватить оба монитора, то выходим из метода
                if (firstLockTaken && secondLockTaken)
                    return;
                // если удалось захватить только один монитор, то освобождаем, чтобы другие потоки могли захватить его
                if (firstLockTaken)
                    lock1.unlock();
                // если удалось захватить только один монитор, то освобождаем, чтобы другие потоки могли захватить его
                if (secondLockTaken)
                    lock2.unlock();
            }
            try {
                // время для захвата мониторов друшими потоками
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }


    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
         /*   //захват двух мониторов с помощью ReentrantLock (аналог synchronized)
            lock1.lock();
            lock2.lock();*/
            takeLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
                // необходимо поместить в блок finally, так как может в transfer выброситься исключение и аккаунты не будут разлочены
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() {
        Random random = new Random();
        // При использовании ReentrantLock можно захватывать мониторы в разном порядке, нужно только прописать условие захвата
        // сразу двух мониторов одним потоком с помощью tryLock().


        for (int i = 0; i < 10000; i++) {
          /*  //захват двух мониторов с помощью ReentrantLock
            lock1.lock();
            lock2.lock();*/
            takeLocks(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
                // необходимо поместить в блок finally, так как может в transfer выброситься исключение и аккаунты не
                // будут разлочены
            } finally {
                lock1.unlock();
                lock2.unlock();
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