package com.nikitasutulov.lab5.task3;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class DecryptionInputStream extends FilterInputStream {
    private final char key;

    public DecryptionInputStream(InputStream in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int encryptedByte = super.read();
        if (encryptedByte == -1) {
            return -1;
        }
        return encryptedByte - key;
    }
}
