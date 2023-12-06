package com.nikitasutulov.lab9.task1;

import java.util.Random;

import static com.nikitasutulov.lab9.task1.Bank.ACCOUNTS_NUMBER;

public class Main {
    public static final int THREADS_NUMBER = 1000;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_NUMBER];
        Bank bank = new Bank();
        System.out.println("Bank balance before random transactions: " + bank.getBalance());

        Random random = new Random();

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Account currentAccount = bank.getAccounts()[Math.abs(random.nextInt() % ACCOUNTS_NUMBER)];
            threads[i] = new Thread(
                    new TransferRunnable(
                            bank,
                            currentAccount,
                            random.nextInt(currentAccount.getBalance())
                    )
            );
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Bank balance after random transactions: " + bank.getBalance());
    }
}
