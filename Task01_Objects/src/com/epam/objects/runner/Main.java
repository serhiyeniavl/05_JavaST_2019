/**
 * Package has one runner class.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package com.epam.objects.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class than starts the program.
 *
 * @author Vladislav Sergienya
 * @version 1.0
 */

final class Main {
    /**
     * Logger for recording a program state.
     */
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Private constructor without param.
     */
    private Main() {
    }

    /**
     * Program initial point.
     * @param args program arguments.
     */
    public static void main(final String[] args) {
        LOGGER.info("Hello world");
    }
}
