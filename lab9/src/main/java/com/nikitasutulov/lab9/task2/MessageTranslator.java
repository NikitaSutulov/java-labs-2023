package com.nikitasutulov.lab9.task2;

public class MessageTranslator extends Thread {
    private CircularBuffer sourceBuffer;
    private CircularBuffer targetBuffer;
    private final int number;

    public MessageTranslator(CircularBuffer sourceBuffer, CircularBuffer targetBuffer, int n) {
        this.sourceBuffer = sourceBuffer;
        this.targetBuffer = targetBuffer;
        number = n;
        setDaemon(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            String message = sourceBuffer.get();
            String translatedMessage = "Потік №" + number + " переклав повідомлення " + message;
            targetBuffer.put(translatedMessage);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}