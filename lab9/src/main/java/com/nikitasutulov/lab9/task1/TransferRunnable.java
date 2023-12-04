package com.nikitasutulov.lab9.task1;

import java.util.Random;

public class TransferRunnable implements Runnable {
    private final Bank bank;
    private final Account fromAccount;
    private final int maxSum;
    TransferRunnable(Bank bank, Account account, int sum) {
        this.bank = bank;
        fromAccount = account;
        maxSum = sum;
    }
    public void run() {
        try {
            Account toAccount;
            do {
                toAccount = bank.getAccounts()[(int)(Math.random() * bank.getAccounts().length)];
            } while (toAccount.equals(fromAccount));
            int someSum = new Random().nextInt(maxSum);
            bank.trans(fromAccount, toAccount, someSum);
        } catch (InterruptedException e) { }
    }
}
