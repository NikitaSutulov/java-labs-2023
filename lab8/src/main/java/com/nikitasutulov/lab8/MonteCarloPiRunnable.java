package com.nikitasutulov.lab8;

import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloPiRunnable implements Runnable {
    private final long iterations;
    private long pointsInsideTheCircle;

    public MonteCarloPiRunnable(long iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        pointsInsideTheCircle = 0;

        for (long i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            double distanceSquared = x * x + y * y;

            if (distanceSquared <= 1.0) {
                pointsInsideTheCircle++;
            }
        }
    }

    public long getResult() {
        return pointsInsideTheCircle;
    }
}
