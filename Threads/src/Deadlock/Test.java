package Deadlock;

import java.util.Random;

public class Test {
    // В данном примере показано состояние (RaceCondition) гонки потоков (не синхронизированный доступ к методам,переменным)
    // в двух других пакетах решения по устранению проблемы
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
            Account.transfer(account1, account2, random.nextInt(100));
        }


    }

    public void secondThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            Account.transfer(account1, account2, random.nextInt(100));
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