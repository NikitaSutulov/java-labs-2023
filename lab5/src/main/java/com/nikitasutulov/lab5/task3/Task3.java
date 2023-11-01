package com.nikitasutulov.lab5.task3;

import java.io.*;

public class Task3 {

    public static void main(String[] args) {
        final char KEY = '\2';

        try {
            FileInputStream inputFileStream = new FileInputStream("input.txt");
            FileOutputStream encryptedFileOutputStream = new FileOutputStream("encrypted.txt");
            EncryptionOutputStream encryptionOutputStream = new EncryptionOutputStream(encryptedFileOutputStream, KEY);

            int byteRead;
            while ((byteRead = inputFileStream.read()) != -1) {
                encryptionOutputStream.write(byteRead);
            }

            inputFileStream.close();
            encryptionOutputStream.close();
            encryptedFileOutputStream.close();

            FileInputStream encryptedFileInputStream = new FileInputStream("encrypted.txt");
            FileOutputStream decryptedFileOutputStream = new FileOutputStream("decrypted.txt");
            DecryptionInputStream decryptionInputStream = new DecryptionInputStream(encryptedFileInputStream, KEY);

            int decryptedByte;
            while ((decryptedByte = decryptionInputStream.read()) != -1) {
                decryptedFileOutputStream.write(decryptedByte);
            }

            encryptedFileInputStream.close();
            decryptedFileOutputStream.close();
            decryptionInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
