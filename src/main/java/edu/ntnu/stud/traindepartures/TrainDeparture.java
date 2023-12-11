package edu.ntnu.stud.traindepartures;

import edu.ntnu.stud.ErrorLogger;
import java.time.LocalTime;

/**
 * The {@code TrainDeparture} represents a train departure. All {@code TrainDeparture}s have a train
 * number, destination, line and departure time.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 *   TrainDeparture trainDeparture = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
 * </pre></blockquote>
 *
 * <p>The class {@code TrainDeparture} includes methods for adding delay and track to a departure.
 * It also allows you to change the train number, destination, line and departure time of a
 * {@code TrainDeparture}.
 *
 * @author Kim Hejer
 * @version 1.1.1
 * @since 1.0.0
 */

public class TrainDeparture {

  private int trainNumber;
  private int track;
  private String destination;
  private String line;
  private LocalTime departureTime;
  private static final LocalTime DEFAULT_TIME = LocalTime.MIN;
  private LocalTime delay = DEFAULT_TIME;
  private static final String ERROR_VALUE = "INVALID";
  private static final int MAX_TRACK = 10;
  private final ErrorLogger logger;


  /**
   * Constructs a new {@code TrainDeparture} with default initial departure time.
   *
   * @since 1.0.0
   */
  public TrainDeparture() {
    // Overriding the default constructor, giving it a default value.
    this.setTrainNumber(0);
    this.setDestination(ERROR_VALUE);
    this.setLine(ERROR_VALUE);
    this.setDepartureTime("00:00");
    this.logger = ErrorLogger.getLogger();
  }

  /**
   * Constructs a new {@code TrainDeparture} by taking train number, destination, line and departure
   * time.
   *
   * <p>The constructor sets invalid values when a given destination or departure time are not
   * valid or null.
   *
   * @param trainNumber   The train number of the train, a positive number above 0.
   * @param destination   The destination of the train.
   * @param line          The line number of the train, a positive number above 0.
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   */
  public TrainDeparture(int trainNumber, String destination, String line,
      String departureTime) {
    this.setTrainNumber(trainNumber);
    this.setDestination(destination);
    this.setLine(line);
    this.setDepartureTime(departureTime);
    this.logger = ErrorLogger.getLogger();
  }

  /**
   * Sets the train number of a {@code TrainDeparture} when a valid {@code int} is provided.
   *
   * <p>The train number is set to 0 when an invalid value is provided.
   * The 0 can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param trainNumber Thr train number as a valid and positive number greater than 0.
   * @since 1.0.0
   */
  public void setTrainNumber(int trainNumber) {
    this.trainNumber = Math.max(trainNumber, 0);
  }

  public void changeTrainNumber(int trainNumber) {
    this.trainNumber = Math.max(trainNumber, 0);
  }

  /**
   * Provides the train number of the {@code TrainDeparture}.
   *
   * @return The train number of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Sets the track of a {@code TrainDeparture} when a valid {@code String} is provided.
   *
   * <p>The track is set to "INVALID" when an invalid value is provided.
   * The "INVALID" can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param track The track the train will be located at as a valid and non-null {@code String}.
   * @since 1.0.0
   */
  public void setTrack(int track) {
    if (track > 0 && track <= MAX_TRACK) {
      this.track = track;
    } else {
      this.track = -1;
    }
  }

  /**
   * Provides the track of the {@code TrainDeparture}.
   *
   * @return The track of the {@code TrainDeparture}.
   */
  public int getTrack() {
    return track;
  }

  /**
   * Sets the destination of a {@code TrainDeparture} when a valid {@code String} is provided.
   *
   * <p>The destination is set to "INVALID" when an invalid value is provided.
   * The "INVALID" can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param destination The destination of the train as a valid and non-null {@code String}.
   * @since 1.0.0
   */
  public void setDestination(String destination) {
    if (validateString(destination)) {
      this.destination = destination.toUpperCase();
    } else {
      this.destination = ERROR_VALUE;
    }
  }

  /**
   * Changes the destination of a {@code TrainDeparture} when a valid {@code String} is provided.
   *
   * <p>The destination is set to "INVALID" when an invalid value is provided.
   * The "INVALID" can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param destination The destination of the train as a valid and non-null {@code String}.
   * @since 1.0.0
   */
  public void changeDestination(String destination) {
    if (validateString(destination)) {
      this.destination = destination.toUpperCase();
    } else {
      this.destination = ERROR_VALUE;
    }
  }

  /**
   * Provides the destination of the {@code TrainDeparture}.
   *
   * @return The destination of the {@code TrainDeparture}.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Provides the line of the {@code TrainDeparture}.
   *
   * @return The line of the {@code TrainDeparture}.
   */
  public String getLine() {
    return line;
  }

  /**
   * Sets the line of a {@code TrainDeparture} when a valid {@code String} is provided.
   *
   * <p>The line is set to "INVALID" when an invalid value is provided.
   * The value "INVALID" can be used to verify if the {@code TrainDeparture} object is valid to
   * use.
   *
   * @param line The line number of the train as a valid and positive number greater than 0.
   * @since 1.0.0
   */
  public void setLine(String line) {
    if (validateString(line)) {
      this.line = line.toUpperCase();
    } else {
      this.line = ERROR_VALUE;
    }
  }

  /**
   * Changes the line of a {@code TrainDeparture} when a valid {@code String} is provided.
   *
   * <p>The line is set to "INVALID" when an invalid value is provided.
   * The value "INVALID" can be used to verify if the {@code TrainDeparture} object is valid to
   * use.
   *
   * @param line The line number of the train as a valid and positive number greater than 0.
   * @since 1.0.0
   */
  public void changeLine(String line) {
    if (validateString(line)) {
      this.line = line.toUpperCase();
    } else {
      this.line = ERROR_VALUE;
    }
  }

  /**
   * Sets the departure time of a {@code TrainDeparture} when a valid {@code String} is provided.
   * The {@code String} is valid if and only if it is written in the format "HH:MM".
   *
   * <p>The departure time is set to "00:00" when an invalid value is provided. The standard time
   * can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   * @see #setTime(String)
   * @since 1.1.0
   */
  public void setDepartureTime(String departureTime) {
    this.departureTime = setTime(departureTime);
  }

  /**
   * Changes the departure time of a {@code TrainDeparture} when a valid
   * {@code String} is provided. The {@code String} is valid if and only if it is written in the
   * format "HH:MM".
   *
   * <p>The departure time is set to "00:00" when an invalid value is provided. The standard time
   * can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   * @see #setTime(String)
   * @since 1.1.0
   */
  public void changeDepartureTime(String departureTime) {
    this.departureTime = setTime(departureTime);
    this.delay = DEFAULT_TIME;
  }

  /**
   * Provides the departure time including the delay of the {@code TrainDeparture} as
   * {@code LocalTime}.
   *
   * @return The departure time of the {@code TrainDeparture}.
   */
  public LocalTime getDepartureTime() {
    return departureTime.plusHours(delay.getHour())
        .plusMinutes(delay.getMinute());
  }

  /**
   * Provides the initial departure time of the {@code TrainDeparture}. The initial time is
   * the departure time, excluding the delay.
   *
   * @return The initial departure time of the {@code TrainDeparture}.
   */
  public LocalTime getInitialDepartureTime() {
    return departureTime;
  }


  /**
   * Sets a delay to the departure time of a {@code TrainDeparture} when a valid {@code String} is
   * provided. The {@code String} is valid if and only if it is written in the format "HH:MM" and
   * the sum of the delay and current departure time is before midnight.
   *
   * <p>The delay is set to "00:00" when an invalid value is provided. The standard time can be used
   * to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param delay The delay to the departure time. Has to be written in the format "HH:MM".
   * @see #setTime(String)
   * @since 1.1.0
   */
  public void setDelay(String delay) {
    LocalTime newDelay = setTime(delay);
    if (departureTime.plusHours(newDelay.getHour())
        .plusMinutes(newDelay.getMinute())
        .isBefore(DEFAULT_TIME)) {
      this.delay = DEFAULT_TIME;
    } else {
      this.delay = newDelay;
    }
  }

  /**
   * Provides the delay of the {@code TrainDeparture}.
   *
   * @return The delay of the {@code TrainDeparture}.
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * The {@code checkTimeString} method checks if a given {@code String} is a valid timeString. The
   * timeString is valid if and only if it is written in the format "HH:MM".
   *
   * <p>Example: "12:00" is a valid timeString, but "1040" are not.
   *
   * @param timeString The timeString to be checked.
   * @return LocalTime object of the given {@code String} if the timeString is valid, standard time
   "00:00" otherwise.
   * @since 1.1.0
    */
  private LocalTime setTime(String timeString) {
    LocalTime output;
    try {
      output = LocalTime.parse(timeString);
    } catch (NumberFormatException e) {
      logger.logError(e.getMessage());
      for (StackTraceElement element : e.getStackTrace()) {
        logger.logError(String.valueOf(element));
      }
      output = DEFAULT_TIME;
    }
    return output;
  }

  /**
   * The {@code validateString} method checks if a given {@code String} is a valid string. The
   * string is valid if and only if it is not null or empty.
   *
   * @param string The string to be checked.
   * @return {@code true} if the string is valid, {@code false} otherwise.
   * @since 1.0.0
   */
  private boolean validateString(String string) {
    return string != null && !string.isEmpty();
  }
}
