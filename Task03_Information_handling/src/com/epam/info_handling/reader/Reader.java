package com.epam.info_handling.reader;

import com.epam.info_handling.exception.FatalRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class Reader was created for read data form file, expect a few situations
 * described in methods. Reader uses Stream API for reading: {@link Path},
 * {@link Stream}, {@link Files}
 *
 * @author Vladislav Sergienya
 * @version 1.0
 */
public class Reader {
    /**
     * Logger for logging errors info.
     *
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);

    /**
     * Read data from file. Checks when file is empty and stops program when
     * input file is empty.
     * <p>
     * Also check on file path error: path is
     * directory, nonexistent file.
     *
     * @return {@link List} of strings from file.
     */
    public String readData(final String filePath) {
        Path path;
        String data;

        if (!isNullablePath(filePath)) {
            path = Paths.get(filePath);
        } else {
            LOGGER.fatal("File path is not defined.");
            throw new FatalRuntimeException(
                    "Argument filePath is null");
        }
        try {
            data = Files.readString(path);
            if (data.isEmpty()) {
                LOGGER.fatal("Input file is empty. Didnt get a data.");
                throw new FatalRuntimeException("File if empty");
            }
        } catch (IOException e) {
            LOGGER.fatal("File does not exist.", e);
            throw new FatalRuntimeException("File didnt get.", e);
        } catch (UncheckedIOException e) {
            LOGGER.fatal("File path is a directory, incorrect input.", e);
            throw new FatalRuntimeException("File didnt get.", e);
        }
        return data;
    }

    /**
     * @param filePath path to file to be read.
     * @return {@code true} when path to file is null, otherwise {@code false}.
     */
    private boolean isNullablePath(final String filePath) {
        return filePath == null;
    }
}
