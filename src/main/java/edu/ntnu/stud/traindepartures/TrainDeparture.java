package edu.ntnu.stud.traindepartures;


import java.time.LocalTime;
// import java.io.InvalidObjectException;
// import java.util.InputMismatchException;
// import javax.management.StringValueExp;

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
 * @version 1.0.0
 * @since 1.0.0
 */

public class TrainDeparture {

  private int trainNumber;
  private int track;
  private String destination;
  private String line;
  private String departureTime;
  private String initialDepartureTime;
  private static final String DEFAULT_TIME = "00:00";
  private String delay = DEFAULT_TIME;
  private static final String ERROR_VALUE = "INVALID";
  private static final int MAX_TRACK = 10;


  /**
   * Constructs a new {@code TrainDeparture} with default initial departure time.
   *
   * @since 1.0.0
   */
  public TrainDeparture() {
    this.initialDepartureTime = DEFAULT_TIME;
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
    this.initialDepartureTime = departureTime;
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
   * The {@code String} is valid if and only if it is written in the format "HH:MM" and the time is
   * a valid time before midnight.
   *
   * <p>The departure time is set to "INVALID" when an invalid value is provided. The "INVALID"
   * can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   * @see #checkTimeString(String)
   * @since 1.0.0
   */
  public void setDepartureTime(String departureTime) {
    try {
      if (checkTimeString(departureTime)) {
        this.departureTime = departureTime;
      } else {
        this.departureTime = ERROR_VALUE;
      }
    } catch (Exception e) {
      this.departureTime = ERROR_VALUE;
    }
  }

  /**
   * Changes the departure time and initial departure time of a {@code TrainDeparture} when a valid
   * {@code String} is provided. The {@code String} is valid if and only if it is written in the
   * format "HH:MM" and the time is a valid time before midnight.
   *
   * <p>The departure time is set to "INVALID" when an invalid value is provided. The "INVALID"
   * can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   * @see #checkTimeString(String)
   * @since 1.0.0
   */
  public void changeDepartureTime(String departureTime) {
    try {
      if (checkTimeString(departureTime)) {
        this.departureTime = departureTime;
        this.delay = DEFAULT_TIME;
      } else {
        this.departureTime = ERROR_VALUE;
      }
    } catch (Exception e) {
      this.departureTime = ERROR_VALUE;
    }
    this.initialDepartureTime = departureTime;
  }

  /**
   * Provides the departure time of the {@code TrainDeparture}.
   *
   * @return The departure time of the {@code TrainDeparture}.
   */
  public String getDepartureTime() {
    return departureTime;
  }

  /**
   * Provides the initial departure time of the {@code TrainDeparture}.
   *
   * @return The initial departure time of the {@code TrainDeparture}.
   */
  public String getInitialDepartureTime() {
    return initialDepartureTime;
  }

  /**
   * Sets a delay to the departure time of a {@code TrainDeparture} when a valid {@code String} is
   * provided. The {@code String} is valid if and only if it is written in the format "HH:MM" and
   * the sum of the delay and current departure time is before midnight.
   *
   * <p>The delay is set to "INVALID" when an invalid value is provided. The "INVALID" can be used
   * to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param delay The delay to the departure time. Has to be written in the format "HH:MM".
   * @see #checkTimeString(String)
   * @since 1.0.0
   */
  public void setDelay2(String delay) {
    try {
      if (checkTimeString(delay)) {
        int delayHour = LocalTime.parse(delay)
            .getHour();
        int delayMinute = LocalTime.parse(delay)
            .getMinute();
        LocalTime newTime = LocalTime.parse(departureTime)
            .plusMinutes(delayMinute)
            .plusHours((delayHour));
        if (newTime.isBefore(LocalTime.parse(departureTime))) {
          this.delay = ERROR_VALUE;
        } else {
          this.delay = delay;
          this.departureTime = newTime.getHour() + ":" + newTime.getMinute();
        }
      } else {
        this.delay = ERROR_VALUE;
      }
    } catch (Exception e) {
      this.delay = ERROR_VALUE;
    }
  }

  /**
   * Sets a delay to the departure time of a {@code TrainDeparture} when a valid {@code String} is
   * provided. The {@code String} is valid if and only if it is written in the format "HH:MM" and
   * the sum of the delay and current departure time is before midnight.
   *
   * <p>If the delay is valid, it will increase the departure time by the delay.
   *
   * <p>The delay is set to "INVALID" when an invalid value is provided. The "INVALID" can be used
   * to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param delay The delay to the departure time. Has to be written in the format "HH:MM".
   * @see #checkTimeString(String)
   * @since 1.0.0
   */
  public void setDelay(String delay) {
    if (checkTimeString(delay)) {
      LocalTime newTime = LocalTime.parse(this.departureTime)
          .plusHours(LocalTime.parse(delay).getHour())
          .plusMinutes(LocalTime.parse(delay).getMinute());
      if (newTime.isBefore(LocalTime.parse(this.departureTime))) {
        this.delay = ERROR_VALUE;
      } else {
        this.delay = delay;
        setDepartureTime(localTimeToString(newTime));
      }
    } else {
      this.delay = ERROR_VALUE;
    }

  }

  /**
   * Provides the delay of the {@code TrainDeparture}.
   *
   * @return The delay of the {@code TrainDeparture}.
   */
  public String getDelay() {
    return delay;
  }

  /**
   * The {@code checkTimeString} method checks if a given {@code String} is a valid timeString. The
   * timeString is valid if and only if it is written in the format "HH:MM" and the time is a valid
   * time before midnight.
   *
   * <p>Example: "12:00" is a valid timeString, but "12:60" and "1040" are not.
   *
   * @param timeString The timeString to be checked.
   * @return {@code true} if the timeString is valid, {@code false} otherwise.
   * @since 1.0.0
   */
  private boolean checkTimeString(String timeString) {
    String[] timeStringList = timeString.split("\\:", 0);
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
        output = false;
      }
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

  /**
   * The {@code localTimeToString} method converts a {@code LocalTime} object to a {@code String}.
   *
   * @param time The LocalTime object to be converted.
   * @return The converted LocalTime object as a String in the format "HH:MM".
   * @see #convertTimeValueToString(int)
   * @since 1.0.0
   */
  private String localTimeToString(LocalTime time) {
    int hour = time.getHour();
    int minute = time.getMinute();
    return convertTimeValueToString(hour) + ":" + convertTimeValueToString(minute);
  }

  /**
   * The {@code convertTimeValueToString} method converts a {@code int} value to a double-digit
   * {@code String}.
   *
   * @param value The int value to be converted.
   * @return The converted int value as a double-digit String.
   * @since 1.0.0
   */
  private String convertTimeValueToString(int value) {
    String valueString;
    if (value < 10) {
      valueString = "0" + value;
    } else {
      valueString = String.valueOf(value);
    }
    return valueString;
  }

}
