package edu.ntnu.stud.trainDepartures;

public class Validator {


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
      // Failsafe termination / graceful termination
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

  // public boolean validateDelay()
}
