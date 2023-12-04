package com.nikitasutulov.lab9.task1;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    public static final int ACCOUNTS_NUMBER = 256;
    private final Account[] accounts = new Account[ACCOUNTS_NUMBER];
    private final Lock balanceLock;
    public Bank() {
        Random random = new Random();
        for (int i = 0; i < ACCOUNTS_NUMBER; i++) {
            accounts[i] = new Account(random.nextInt(1, 1000000));
        }
        balanceLock = new ReentrantLock();
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void trans(Account from, Account to, int sum) throws InterruptedException{
        balanceLock.lock();
        try {
            System.out.print(Thread.currentThread());
            try {
                from.withdraw(sum);
            } catch (RuntimeException e) {
                System.out.println(" " + e.getMessage());
                return;
            }
            System.out.print(" " + sum + " from " + from + " to " + to + ". ");
            to.deposit(sum);
            System.out.println("All the accounts balances together = " + getBalance());
        } finally {
            balanceLock.unlock();
        }
    }

    public int getBalance() {
        balanceLock.lock();
        try {
            int sum = 0;
            for (Account account : accounts)
                sum += account.getBalance();
            return sum;
        } finally {
            balanceLock.unlock();
        }
    }
}
