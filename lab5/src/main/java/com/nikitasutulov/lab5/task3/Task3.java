package com.nikitasutulov.lab5.task3;

import java.io.*;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        final char KEY = '\2';

        String[] filePathes = new String[3];
        String[] messages = {
                "Enter the file path to get the bytes stream from",
                "Enter the file path to put the encrypted stream to",
                "Enter the file path to put the decrypted stream to"
        };

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < messages.length; i++) {
                System.out.println(messages[i]);
                filePathes[i] = scanner.nextLine();
            }
        }

        String inputFilePath = filePathes[0];
        String encryptedFilePath = filePathes[1];
        String decryptedFilePath = filePathes[2];

        try {
            FileInputStream inputFileStream = new FileInputStream(inputFilePath);
            FileOutputStream encryptedFileOutputStream = new FileOutputStream(encryptedFilePath);
            EncryptionOutputStream encryptionOutputStream = new EncryptionOutputStream(encryptedFileOutputStream, KEY);

            int byteRead;
            while ((byteRead = inputFileStream.read()) != -1) {
                encryptionOutputStream.write(byteRead);
            }

            inputFileStream.close();
            encryptionOutputStream.close();
            encryptedFileOutputStream.close();

            FileInputStream encryptedFileInputStream = new FileInputStream(encryptedFilePath);
            FileOutputStream decryptedFileOutputStream = new FileOutputStream(decryptedFilePath);
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
