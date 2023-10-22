package edu.ntnu.stud.trainDepartures;

// import java.time.LocalTime;
import java.util.Objects;
/**
 * The {@code TrainDeparture} represents a train departure. All {@code TrainDeparture}s
 * have a train number, destination, line and departure time.
 *
 * <p>Example:
 *
 * <blockquote><pre>
 *   TrainDeparture trainDeparture = new TrainDeparture(123, "Gjøvik", 12, "12:00");
 * </pre></blockquote>
 *
 * <p>The class {@code TrainDeparture} includes methods for adding delay to a departure.
 *
 * @author Kim Hejer
 * @version 1.0.0
 * @since 1.0.0
 */

public class TrainDeparture {
  private int trainNumber;
  private String track;
  private String destination;
  private int line;
  private String departureTime;
  private String delay = "00:00";
  private String errormessage = "INVALID";

  /**
   * Constructs a new {@code TrainDeparture} by taking train number, destination, line and departure
   * time.
   *
   * <p>The constructor sets invalid values when a given destination or departure time are not valid
   * or null.
   *
   * @param trainNumber   The train number of the train, a positive number above 0.
   * @param destination   The destination of the train.
   * @param line          The line number of the train, a positive number above 0.
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   */
  public TrainDeparture(int trainNumber, String destination, int line,
                        String departureTime) {
    this.setTrainNumber(trainNumber);
    this.setDestination(destination);
    this.setLine(line);
    this.setDepartureTime(departureTime);
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
  public void setTrack(String track) {
    this.track = Objects.requireNonNullElse(track, errormessage);
  }

  /**
   * Provides the destination of the {@code TrainDeparture}.
   *
   * @return The destination of the {@code TrainDeparture}.
   */
  public String getTrack() {
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
    this.destination = Objects.requireNonNullElse(destination, errormessage);
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
  public int getLine() {
    return line;
  }

  /**
   * Sets the line of a {@code TrainDeparture} when a valid {@code int} is provided.
   *
   * <p>The line is set to 0 when an invalid value is provided.
   * The 0 can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param line The line number of the train as a valid and positive number greater than 0.
   * @since 1.0.0
   */
  public void setLine(int line) {
    this.line = Math.max(line, 0);
  }

  /**
   * Sets the departure time of a {@code TrainDeparture} when a valid {@code String} is provided.
   * The {@code String} is valid if and only if it is written in the format "HH:MM" and the time
   * is a valid time before midnight.
   *
   * <p>The departure time is set to "INVALID" when an invalid value is provided. The "INVALID"
   * can be used to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param departureTime The time of departure. Has to be written in the format "HH:MM".
   * @since 1.0.0
   * @see #checkTimeString(String)
   */
  public void setDepartureTime(String departureTime) {
    if (checkTimeString(departureTime)) {
      this.departureTime = departureTime;
    } else {
      this.departureTime = errormessage;
    }
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
   * Sets a delay to the departure time of a {@code TrainDeparture} when a valid {@code String} is
   * provided. The {@code String} is valid if and only if it is written in the format "HH:MM" and
   * the time is a valid time before midnight.
   *
   * <p>The delay is set to "INVALID" when an invalid value is provided. The "INVALID" can be used
   * to verify if the {@code TrainDeparture} object is valid to use.
   *
   * @param delay The delay to the departure time. Has to be written in the format "HH:MM".
   * @since 1.0.0
   * @see #checkTimeString(String)
   */
  public void addDelay(String delay) {
    if (checkTimeString(delay)) {
      this.delay = delay;
    } else {
      this.delay = errormessage;
    }
  }

  /**
   * The {@code checkTimeString} method checks if a given {@code String} is a valid timeString.
   * The timeString is valid if and only if it is written in the format "HH:MM" and the time
   * is a valid time before midnight.
   *
   * <p>Example: "12:00" is a valid timeString, but "12:60" is not.
   *
   * @param timeString The timeString to be checked.
   * @return {@code true} if the timeString is valid, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean checkTimeString(String timeString) {
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
        if (hourInt > 23 || minuteInt > 59) {
          output = false;
        }
      } catch (Exception e) {
        output = false;
      }
    }
    return output;
  }
  /*

  private int[] stringToTime(String timeString) {
    String[] timeStringList = timeString.split("\\:");
    int hour = Integer.parseInt(timeStringList[0]);
    int minute = Integer.parseInt(timeStringList[1]);
    return new int[]{hour, minute};
  }

  private String timeToString(int hour, int minute) {
    return hour + "" + minute;
  }

  */
}
