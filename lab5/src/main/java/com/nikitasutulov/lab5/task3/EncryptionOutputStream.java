package com.nikitasutulov.lab5.task3;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class EncryptionOutputStream extends FilterOutputStream {
    private final char key;

    public EncryptionOutputStream(OutputStream out, char key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b + key);
    }
}
