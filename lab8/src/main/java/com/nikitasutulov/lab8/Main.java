package com.nikitasutulov.lab8;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numThreads;
        final long iterations = 1_000_000_000L;
        double startTime, endTime, totalTime;

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of threads: ");
            if (scanner.hasNextInt()) {
                numThreads = scanner.nextInt();
            } else {
                throw new InputMismatchException("Expected the input to be an integer number.");
            }
        }

        MonteCarloPiThread[] threads = new MonteCarloPiThread[numThreads];
        Thread[] workerThreads = new Thread[numThreads];

        startTime = (double) System.nanoTime() / 1000000F;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new MonteCarloPiThread(iterations / numThreads);
            workerThreads[i] = new Thread(threads[i]);
            workerThreads[i].start();
        }

        long totalInsideCircle = 0;

        try {
            for (int i = 0; i < numThreads; i++) {
                workerThreads[i].join();
                totalInsideCircle += threads[i].getResult();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double piApproximation = 4.0 * totalInsideCircle / iterations;

        endTime = (double) System.nanoTime() / 1000000F;
        totalTime = endTime - startTime;

        System.out.println("PI is " + piApproximation);
        System.out.println("THREADS " + numThreads);
        System.out.println("ITERATIONS " + NumberFormat.getNumberInstance().format(iterations));
        System.out.println("TIME " + String.format("%.2f", totalTime) + "ms");
    }
}