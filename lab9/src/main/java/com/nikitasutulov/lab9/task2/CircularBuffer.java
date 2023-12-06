package com.nikitasutulov.lab9.task2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer {
    private final LinkedList<String> buffer;
    private final int capacity;
    private volatile int readIndex;
    private volatile int writeIndex;

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public CircularBuffer(int capacity) {
        buffer = new LinkedList<>();
        this.capacity = capacity;
        readIndex = writeIndex = 0;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public boolean isEmpty() {
        return readIndex == writeIndex;
    }

    public boolean isFull() {
        return (readIndex + 1) % capacity == writeIndex;
    }

    public void put(String value) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }

            if (writeIndex == capacity) {
                writeIndex = 0;
            }
            int oldIndex = writeIndex;
            try {
                synchronized (this) {
                    writeIndex += 1;
                }
                buffer.set(writeIndex, value);
            } catch (IndexOutOfBoundsException e) {
                buffer.add(oldIndex, value);
            }

            // Notify the consumer that the buffer is not empty
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }

            if (readIndex == capacity) {
                readIndex = 0;
            }
            String result = buffer.get(readIndex);
//            buffer.set(readIndex, null);
            synchronized (this) {
                readIndex++;
            }

            // Notify the producer that the buffer is not full
            notFull.signal();

            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
