package edu.ntnu.stud;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The {@code ErrorLogger} class logs errors to the file "error_log".
 *
 * <p>The {@code ErrorLogger} class contains methods for logging errors to a file. The class uses
 * the {@code Logger} class from the {@code java.util.logging} package.
 *
 * <p>The {@code ErrorLogger} class is a singleton class. This means that only one instance of the
 * class can exist at a time. The class has a private constructor and a static method for getting
 * the instance of the class.
 *
 * <p>The {@code ErrorLogger} class uses the {@code Singleton} design pattern.
 *
 * @see Logger
 * @see SimpleFormatter
 * @see FileHandler
 * @see RuntimeException
 * @see ErrorLogger
 * @see <a href="https://refactoring.guru/design-patterns/singleton">Singleton design pattern</a>
 * @since 1.0.0
 */
public class ErrorLogger {
  // Les opp om singleton bruk https://refactoring.guru/design-patterns/singleton
  private static ErrorLogger errorLogger;
  private Logger logger;

  private ErrorLogger() {
    initializeLogger();
  }

  /**
   * The {@code getLogger} method returns the instance of the {@code ErrorLogger} class.
   *
   * <p>If the instance does not exist, the method creates a new instance of the {@code ErrorLogger}
   * class and returns it.
   *
   * @return The instance of the {@code ErrorLogger} class.
   * @since 1.0.0
   */
  public static ErrorLogger getLogger() {
    if (errorLogger == null) {
      errorLogger = new ErrorLogger();
    }
    return errorLogger;
  }

  /**
   * The {@code logError} method logs an error to the file "error_log.txt".
   *
   * <p>The method takes a {@code String} as input and logs it to the file "error_log.txt".
   *
   * @param message The error message to be logged.
   * @since 1.0.0
   */
  public void logError(String message) {
    logger.severe(message);
  }

  private void initializeLogger() {
    logger = Logger.getLogger(ErrorLogger.class.getName());
    logger.setUseParentHandlers(false);
    SimpleFormatter formatter = new SimpleFormatter();
    FileHandler handler;

    try {
      handler = new FileHandler("error_log.txt");
    } catch (IOException e) {
      throw new RuntimeException("Unable to initialize logger", e);
      // Explenation of RuntimeException
      // https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/
      // https://www.baeldung.com/java-runtime-exceptions
      // https://www.baeldung.com/java-new-custom-exception
      // https://www.baeldung.com/java-exceptions
      // https://www.baeldung.com/java-exception-handling
      // Disk problemer
    }
    handler.setFormatter(formatter);
    logger.addHandler(handler);
  }


}
