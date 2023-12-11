package edu.ntnu.stud.traindeparturetools;

import edu.ntnu.stud.ErrorLogger;
import java.time.LocalTime;

/**
 * The {@code Validator} class contains methods for validating user input.
 *
 * <p>The {@code Validator} class contains methods for validating user input. The methods check if
 * the input is valid and return {@code true} if it is valid and {@code false} otherwise.
 *
 * <p>The {@code Validator} class is used by the {@code InputHandler} class.
 *
 * @author Kim Hejer
 * @version 1.1.1
 * @since 1.0.0
 * @see InputHandler
 */

public class Validator {

  private final ErrorLogger logger;


  public Validator() {
    logger = ErrorLogger.getLogger();
  }


  /**
   * The {@code checkTimeString} method checks if a given {@code String} is a valid timeString.
   * The timeString is valid if and only if it is written in the format "HH:MM" and the time
   * is a valid time before midnight.
   *
   * <p>Example: "12:00" is a valid timeString, but "12:60" and "2150" are not.
   *
   * @param timeString The timeString to be checked.
   * @return {@code true} if the timeString is valid, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean checkTimeString(String timeString) {
    String[] timeStringList = timeString.split(":", 0);
    boolean output = true;
    if (timeStringList.length != 2) {
      output = false;
    } else {
      String hour = timeStringList[0];
      String minute = timeStringList[1];
      if (hour.length() != 2 || minute.length() != 2) {
        output = false;
      }
      try {
        int hourInt = Integer.parseInt(hour);
        int minuteInt = Integer.parseInt(minute);
        if (hourInt > 23 || hourInt < 0 || minuteInt < 0 || minuteInt > 59) {
          output = false;
        }
      } catch (NumberFormatException e) {
        logger.logError(e.getMessage());
        for (StackTraceElement element : e.getStackTrace()) {
          logger.logError(String.valueOf(element));
        }
        output = false;
      }
    }
    return output;
  }

  /**
   * The {@code validateString} method checks if a given {@code String} is a valid string.
   * The string is valid if and only if it is not null or empty.
   *
   * @param string The string to be checked.
   * @return {@code true} if the string is valid, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean validateString(String string) {
    return string != null && !string.isEmpty();
  }

  /**
   * The {@code validateDelay} method checks if a given {@code String} is a valid delay.
   * The delay is valid if and only if it is written in the format "HH:MM" and the sum of the time
   * and the delay is after the current time.
   *
   * @param departureTime The departure time of the {@code TrainDeparture} object to be checked.
   * @param delay         The delay of the {@code TrainDeparture} object to be checked.
   * @param currentTime   The current time.
   * @return {@code true} if the delay is valid, {@code false} otherwise.
   * @since 1.1.0
   */
  public boolean validateDelay(LocalTime departureTime, LocalTime delay, LocalTime currentTime) {
    return departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute())
        .isAfter(currentTime);
  }
}
