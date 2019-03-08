package com.epam.threads.io;

import com.epam.threads.exception.FatalErrorRuntimeException;
import com.epam.threads.exception.InvalidArgumentException;
import com.epam.threads.exception.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Class was created for writing data into file using {@link Path} and
 * {@link Files}.
 */
public class Writer {
    /**
     * Logger for log errors.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Writer.class);

    /**
     * File path.
     */
    private Path path;

    /**
     * Class random for generate random numbers.
     */
    private Random random;

    /**
     * Constructor - initializes file path to write in it. Creates object of
     * class {@link Random}. Throws RuntimeException and stops the program
     * when algorithm for random didnt find.
     *
     * @param filePath path to file.
     * @throws NullArgumentException when file path is null.
     */
    public Writer(final String filePath) throws NullArgumentException {
        if (filePath == null) {
            throw new NullArgumentException("File path is null.");
        }
        path = Paths.get(filePath);
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("No algorithm is available for SecureRandom.", e);
            throw new FatalErrorRuntimeException("Program cannot be running "
                    + "without data in source file.");
        }
    }

    /**
     * Writes to file integers in defined range (from bound1 to bound1).
     *
     * @param quantity quantity of elements need to write in file.
     * @param bound1   initial random boundary.
     * @param bound2   max number to random.
     * @throws InvalidArgumentException when file path is invalid.
     */
    public void writeRandomInteger(final int quantity, final int bound1,
                                   final int bound2)
            throws InvalidArgumentException {
        StringBuilder dataStr = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            dataStr.append(random.nextInt(bound2 - bound1) + bound1);
            dataStr.append(" ");
        }
        try {
            Files.write(path, dataStr.toString().getBytes());
        } catch (IOException e) {
            throw new InvalidArgumentException("Invalid file path.", e);
        }
    }
}
