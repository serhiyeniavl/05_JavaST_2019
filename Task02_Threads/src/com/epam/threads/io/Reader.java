package com.epam.threads.io;

import com.epam.threads.exception.EmptyFileException;
import com.epam.threads.exception.NullArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
     * List where data from file will be stored.
     */
    private List<String> stringList = new ArrayList<>();

    /**
     * File path.
     * @see Path
     */
    private Path path;

    /**
     * Logger for logging errors info.
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);

    /**
     * Private constructor for not allow to create class object without file
     * path.
     */
    private Reader() {
    }

    /**
     * Constructor - creates new object with file path.
     * @param filePath path to file for reading info from.
     * @throws NullArgumentException when specified path is null.
     */
    public Reader(final String filePath) throws NullArgumentException {
        if (!isNullablePath(filePath)) {
            path = Paths.get(filePath);
        } else {
            throw new NullArgumentException("Argument filePath is null");
        }
    }

    /**
     * @return read {@link List} of file data.
     */
    public List<String> getStringList() {
        readData();
        return stringList;
    }

    /**
     * Read data from file. Checks when file is empty and catch
     * {@link com.epam.threads.exception.EmptyFileException}.
     * Also check on file path error: path is
     * directory, nonexistent file.
     */
    private void readData() {
        try (Stream<String> stringStream = Files.lines(path)) {
            stringList = stringStream.collect(Collectors.toList());
            if (stringList.isEmpty()) {
                throw new EmptyFileException("File if empty");
            }
        } catch (IOException e) {
            LOGGER.error("File does not exist.", e);
        } catch (UncheckedIOException e) {
            LOGGER.error("File path is a directory, incorrect input.", e);
        } catch (EmptyFileException e) {
            LOGGER.error("Specified file is empty", e);
        }
    }

    /**
     * @param filePath path to file to be read.
     * @return {@code true} when path to file is null, otherwise {@code false}.
     */
    private boolean isNullablePath(final String filePath) {
        return filePath == null;
    }
}
