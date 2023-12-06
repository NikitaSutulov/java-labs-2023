package com.nikitasutulov.lab9.task2;

public class MessageProducer extends Thread {
    private final CircularBuffer sourceBuffer;
    private final int number;

    public MessageProducer(CircularBuffer sourceBuffer, int n) {
        this.sourceBuffer = sourceBuffer;
        number = n;
        setDaemon(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            String message = "Потік №" + number + " згенерував повідомлення " + i;
            try {
                sourceBuffer.put(message);
                sleep(100); // Додаткова затримка для імітації генерації повідомлень
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
