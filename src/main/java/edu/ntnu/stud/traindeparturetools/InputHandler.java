package edu.ntnu.stud.traindeparturetools;

import edu.ntnu.stud.ErrorLogger;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * The {@code InputHandler} class contains methods for handling user input.
 *
 * <p>The {@code InputHandler} class contains methods for handling user inputs. The methods
 * validate the input and return the input if it is valid. If the input is invalid, the user is
 * prompted to enter a new input.
 *
 * @author Kim Hejer
 * @version 1.1.0
 * @since 1.0.0
 * @see Validator
 * @see Printer
 */

public class InputHandler {

  private final Scanner userInput;
  private final Validator validator;
  private final Printer printer;
  private final ErrorLogger logger;

  /**
   * The {@code InputHandler} constructor creates a new {@code Scanner} object, a new
   * {@code Validator} object and a new {@code Printer} object.
   *
   * @since 1.0.0
   */
  public InputHandler() {
    userInput = new Scanner(System.in);
    validator = new Validator();
    printer = new Printer();
    logger = ErrorLogger.getLogger();
  }


  /**
   * The {@code intInputHandlerPositive} method handles user input of positive integers above 0.
   *
   * <p>The method prompts the user to enter an integer. If the input is not an integer or an
   * integer below 0, the user is prompted to enter a new input. The method returns the input if it
   * is a positive integer above 0.
   *
   * @return The positive integer entered by the user.
   * @since 1.0.0
   */
  public int intInputHandlerPositive() {
    String menuChoice;
    int output = 0;
    do {
      menuChoice = userInput.nextLine();
      try {
        output = Integer.parseInt(menuChoice);
        if (output <= 0) {
          printer.printErrorMessage(
              "The number has to be positive and of higher value than zero. Try again.");
        }

      } catch (NumberFormatException e) {
        logger.logError(e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
          logger.logError(String.valueOf(element));
        }
        printer.printErrorMessage(1);
      }
    } while (output <= 0);
    return output;
  }

  /**
   * The {@code intInputHandler} method handles user input of integers.
   *
   * <p>The method prompts the user to enter an integer. If the input is not an integer, the user is
   * prompted to enter a new input. The method returns the input if it is an integer.
   *
   * @return The integer entered by the user.
   * @since 1.0.0
   */
  public int choiceHandler(int min, int max) {
    int output = intInputHandlerPositive();
    while (output < min || output > max) {
      printer.printChoiceError(min, max);
      output = intInputHandlerPositive();
    }
    return output;
  }

  /**
   * The {@code stringInputHandler} method handles user input of strings.
   *
   * <p>The method prompts the user to enter a string. If the input is null or empty, the user is
   * prompted to enter a new input. The method returns the input if it is a valid {code String}.
   *
   * @param message The message to be printed to the user.
   * @return The string entered by the user.
   * @since 1.0.0
   * @see Validator
   */
  public String stringInputHandler(String message) {
    printer.printMessage(message);
    String stringInput = userInput.nextLine();
    while (!validator.validateString(stringInput)) {
      printer.printErrorMessage(3);
      printer.printMessage(message);
      stringInput = userInput.nextLine();
    }
    return stringInput;
  }

  /**
   * The {@code setTimeString} method handles user input of timeStrings.
   *
   * <p>The method prompts the user to enter a timeString. If the input is not a valid timeString,
   * the user is prompted to enter a new input. The method returns the input if it is a valid
   * timeString. The string is valid if and only if it is written in the format "HH:MM" and the time
   * is a valid time before midnight.
   *
   * @param message The message to be printed to the user.
   * @return The timeString entered by the user.
   * @since 1.0.0
   * @see Validator
   */
  public String setTimeString(String message) {
    printer.printMessage(message);
    String timeStringFormat = "The format has to be HH:MM, for example 12:00";
    String timeString = stringInputHandler(timeStringFormat);
    while (!validator.checkTimeString(timeString)) {
      printer.printErrorMessage(4);
      printer.printMessage(message);
      printer.printTimeStringFormat();
      timeString = userInput.nextLine();
    }
    return timeString;
  }

  /**
   * The {@code departureTimeStringMaker} method handles user input of departure time String.
   * The method prompts the user to enter a departure time String. If the input is not a valid
   * departure time String, the user is prompted to enter a new input. The method returns the input
   * if it is a valid departure time String. The {@code String} is valid if and only if it is
   * written in the format "HH:MM" and the time is a valid time before midnight and after the
   * current time.
   *
   * @param currentTime The current time of the program.
   * @return The departureTimeString entered by the user.
   */
  public String departureTimeStringMaker(LocalTime currentTime) {
    String departureTime = setTimeString("When is the train departing?");
    while (currentTime.isAfter(LocalTime.parse(departureTime))
        && !LocalTime.parse(departureTime).equals(currentTime)) {
      printer.printErrorMessage(5);
      departureTime = setTimeString("When is the train departing?");
    }
    return departureTime;
  }

  /**
   * The {@code setDepartureTimeString} method handles user input of delay String.
   *
   * <p>The method prompts the user to enter a delay String. If the input is not a valid
   * delay String, the user is prompted to enter a new input. The method returns the input if
   * it is a valid delay String. The {@code String} is valid if and only if it is written in the
   * format "HH:MM" and the time is a valid time before midnight and after the current time.
   *
   * @param currentTime The current time.
   * @return The departureTimeString entered by the user.
   * @since 1.0.0
   * @see Validator
   */
  public String setDelayString(LocalTime departureTime, LocalTime currentTime) {
    String delayString = setTimeString("How much time would you like to delay the departure with?");
    while (!validator.validateDelay(departureTime, LocalTime.parse(delayString), currentTime)) {
      printer.printErrorMessage(
          "The delay cannot make the departure time exceed midnight. Try again.");
      delayString = setTimeString("How much time would you like to delay the departure with?");
    }
    return delayString;
  }

}