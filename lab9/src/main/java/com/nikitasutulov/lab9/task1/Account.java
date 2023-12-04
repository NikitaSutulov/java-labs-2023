package com.nikitasutulov.lab9.task1;

public class Account {
    private int balance;

    public Account(int b) {
        balance = b;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (amount > balance || amount < 0) {
            throw new RuntimeException("Not enough balance: " + balance + " when " + amount + " is requested to transfer");
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        if (amount < 0) {
            return;
        }
        balance += amount;
    }

    @Override
    public String toString() {
        return "Account with balance " + balance;
    }
}
