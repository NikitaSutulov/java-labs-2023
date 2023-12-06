package com.nikitasutulov.lab9.task2;

public class Main {
    public static void main(String[] args) {
        CircularBuffer buffer1 = new CircularBuffer(50);
        CircularBuffer buffer2 = new CircularBuffer(10);

        MessageProducer[] producers = new MessageProducer[5];
        MessageTranslator[] translators = new MessageTranslator[2];

        for (int i = 0; i < 5; i++) {
            producers[i] = new MessageProducer(buffer1, i + 1);
            producers[i].start();
        }

        for (int i = 0; i < 2; i++) {
            translators[i] = new MessageTranslator(buffer1, buffer2, i + 6);
            translators[i].start();
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(buffer2.get());
        }
    }
}