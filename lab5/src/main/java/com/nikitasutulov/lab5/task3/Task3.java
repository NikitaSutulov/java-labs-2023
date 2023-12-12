package com.nikitasutulov.lab5.task3;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Task3 {

    private static final Logger logger = LogManager.getLogger(Task3.class);
    private static final char KEY = '\2';
    private static final String MESSAGES_BUNDLE_BASENAME = "location.messages";

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.DEBUG); // if changed to INFO the DEBUG messages will not be logged
        String[] filePaths = new String[3];

        Locale locale = chooseLocale();
        ResourceBundle messages = ResourceBundle.getBundle(MESSAGES_BUNDLE_BASENAME, locale);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            logger.info(messages.getString("enterInputFilePath"));
            filePaths[0] = reader.readLine();
            logger.info(messages.getString("enterEncryptedFilePath"));
            filePaths[1] = reader.readLine();
            logger.info(messages.getString("enterDecryptedFilePath"));
            filePaths[2] = reader.readLine();
        } catch (IOException e) {
            logger.fatal(messages.getString("inputError") + ": " + e.getMessage());
            return;
        }

        String inputFilePath = filePaths[0];
        String encryptedFilePath = filePaths[1];
        String decryptedFilePath = filePaths[2];

        try {
            logger.debug(messages.getString("encrypting"));
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
            logger.debug(messages.getString("encryptionComplete"));

            logger.debug(messages.getString("decrypting"));
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
            logger.debug(messages.getString("decryptionComplete"));

        } catch (IOException e) {
            logger.fatal(messages.getString("error") + ": " + e.getMessage().split("\n")[0], e);
        }
    }

    private static Locale chooseLocale() {
        logger.info("Choose a locale: (1) English, (2) Italiano, (3) Deutsch");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    return Locale.ENGLISH;
                case 2:
                    return Locale.ITALIAN;
                case 3:
                    return Locale.GERMAN;
                default:
                    logger.warn("Invalid choice. Using default locale (English).");
                    return Locale.ENGLISH;
            }
        } catch (IOException | NumberFormatException e) {
            logger.warn("Error reading choice. Using default locale (English).");
            return Locale.ENGLISH;
        }
    }

}
