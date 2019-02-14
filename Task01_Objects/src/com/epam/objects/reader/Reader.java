package com.epam.objects.reader;

import com.epam.objects.exception.MissingFilePathException;
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
 * described in methods. Reader uses Java 8 tools for reading: {@link Path},
 * {@link Stream}, {@link Files}
 * @author Vladislav Sergienya
 * @version 1.0
 */
public class Reader {
    private List<String> stringList = new ArrayList<>();
    private Path path;
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);

    private Reader() {
    }

    public Reader(final String filePath) throws MissingFilePathException {
        if (filePath != null) {
            path = Paths.get(filePath);
        } else {
            throw new MissingFilePathException("Argument filePath is null");
        }
    }

    public List<String> getStringList() {
        readData();
        return stringList;
    }

    private void readData() {
        try (Stream<String> stringStream = Files.lines(path)) {
            stringList = stringStream.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("File reading error.");
        } catch (NullPointerException e) {
            LOGGER.error("Program did not get a file: path is null");
        } catch (UncheckedIOException e) {
            LOGGER.error("File path is a directory, incorrect input.");
        }
    }
}
