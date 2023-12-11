package edu.ntnu.stud.traindeparturetools;

import edu.ntnu.stud.traindeparturegui.TrainDepartureGUI;
import edu.ntnu.stud.traindepartures.TrainDeparture;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * The {@code Printer} class prints messages to the user for the {@code TrainDepartureGUI} class.
 *
 * <p>The {@code Printer} class contains methods for printing messages to the user. The methods
 * print messages to the console.
 *
 * @author Kim Hejer
 * @version 1.1.1
 * @since 1.0.0
 * @see TrainDepartureGUI
 * @see InputHandler
 */
public class Printer {

  private final StringBuilder stringBuilder;


  public Printer() {
    stringBuilder = new StringBuilder();

  }

  /**
   * The {@code printDepartureDetails} method prints the details of a {@code TrainDeparture} object.
   * The method prints the departure time, line, train number, destination and track of the
   * {@code TrainDeparture} object.
   *
   * <p>If the {@code TrainDeparture} object has a delay, the initial departure time is printed
   * with a strike-through font and the current departure time is printed next to it.
   *
   * <p>If the {@code TrainDeparture} object does not have a track, the track is printed as "N/A".
   * The track is otherwise printed as an integer.
   *
   * @param trainDeparture The {@code TrainDeparture} object to be printed.
   */
  public void printDepartureDetails(TrainDeparture trainDeparture) {
    System.out.print(" ");
    if (!trainDeparture.getDelay().equals(LocalTime.MIN)) {
      System.out.format("%-5s", strikeThrough("" + trainDeparture.getInitialDepartureTime()));
      System.out.format("%3s", "");
      System.out.format("%-10s", trainDeparture.getDepartureTime());
    } else {
      System.out.format("%-18s", trainDeparture.getDepartureTime());
    }
    System.out.format("%-8s", trainDeparture.getLine());
    System.out.format("%-10s", trainDeparture.getTrainNumber());
    System.out.format("%-15s", trainDeparture.getDestination());
    if (trainDeparture.getTrack() != 0) {
      System.out.format("%-2s", trainDeparture.getTrack());
    } else {
      System.out.format("%-2s", "N/A");
    }
    System.out.println();
  }

  private String strikeThrough(String str) {
    return str.chars()
        .mapToObj(c -> (char) c + "̶") // "̶" = "\u0336"
        .collect(Collectors.joining());
  }

  /**
   * The {@code printMenuChoices} method prints the main menu-choices for the user.
   */
  public void printMenuChoices() {
    resetStringBuilder();
    stringBuilder.append("What would you like to do? Enter a number between 1 and 9.")
        .append("\n")
        .append("1. Add a new train departure")
        .append("\n")
        .append("2. Change a train departure")
        .append("\n")
        .append("3. Add delay to a train departure")
        .append("\n")
        .append("4. Assign track to departure")
        .append("\n")
        .append("5. Search for a train departure")
        .append("\n")
        .append("6. Print all train departures")
        .append("\n")
        .append("7. Change time")
        .append("\n")
        .append("8. Exit");
    System.out.println(stringBuilder);
  }

  /**
   * The {@code printSearchMenuChoices} method prints the search menu-choices for the user.
   */
  public void printSearchMenuChoices() {
    resetStringBuilder();
    stringBuilder.append("What would you like to do? Enter a number between 1 and 2.")
        .append("\n")
        .append("1. Search for departure by train number.")
        .append("\n")
        .append("2. Search for departures with the same destination.");
    System.out.println(stringBuilder);
  }

  /**
   * The {@code printChangeMenuChoices} method prints the change menu-choices for the user.
   */
  public void printChangeMenuChoices() {
    resetStringBuilder();
    stringBuilder.append("What would yo like to edit? Enter a number between 1 and 4.")
        .append("\n")
        .append("1. Change Train Number")
        .append("\n")
        .append("2. Change Line")
        .append("\n")
        .append("3. Change Destination")
        .append("\n")
        .append("4. Change Departure Time");
    System.out.println(stringBuilder);
  }

  private void resetStringBuilder() {
    if (!stringBuilder.isEmpty()) {
      stringBuilder.setLength(0);
    }
  }

  /**
   * The {@code printChangeTimeMenuChoices} method prints the welcome message to the user.
   */
  public void printWelcomeMessage() {
    System.out.println("Welcome to the train dispatch application! Version 1.1.1");
  }

  /**
   * The {@code printTimeStringFormat} method prints the format of a timeString.
   */
  public void printTimeStringFormat() {
    String timeStringFormat = "The format has to be HH:MM, for example 12:00";
    System.out.println(timeStringFormat);
  }

  /**
   * The {@code printMessage} method prints a message to the user.
   *
   * @param choice The choice of message to be printed.
   */
  public void printMessage(int choice) {
    switch (choice) {
      case 1 -> System.out.println("What is the train number?");
      case 2 -> System.out.println("When is the train departing?");
      default -> System.err.println("Choice out of bounds");
    }
  }

  /**
   * The {@code printMessage} method prints a given message to the user.
   */
  public void printMessage(String message) {
    System.out.println(message);
  }

  /**
   * The {@code printErrorMessage} method prints a given error message to the user.
   */
  public void printErrorMessage(String message) {
    System.err.println(message);
  }

  /**
   * The {@code printErrorMessage} contains various error messages that can be printed to the user.
   * The method prints the error message corresponding to the given choice.
   *
   * <p>The error messages are:
   *
   * <p>1. Invalid input, the input has to be an integer. Try again.
   *
   * <p>2. Invalid input, the input has to be an integer between 1 and 9. Try again.
   *
   * <p>3. The value cannot be empty or null. Try again.
   *
   * <p>4. Invalid input, try again.
   *
   * <p>5. Invalid input, the departure time cannot be before the current time. Try again.
   *
   * <p>6. There is already a departure with that train number, try a new one.
   *
   * <p>7. Invalid input, the input has to be an integer between 1 and 2. Try again.
   *
   * <p>8. There is no departure with that train number.
   *
   * <p>9. There is already a train with the same line and departure time.
   *
   * <p>10. Try again.
   *
   * <p>11. There is already a train with the same track and departure time.
   *
   * @param choice The choice of error message to be printed.
   */
  public void printErrorMessage(int choice) {
    switch (choice) {
      case 1 -> System.err.println("Invalid input, the input has to be an integer. Try again.");
      case 2 -> System.err.println(
          "Invalid input, the input has to be an integer between 1 and 8. Try again.");
      case 3 -> System.err.println("The value cannot be empty or null. Try again.");
      case 4 -> System.err.println("Invalid input, try again");
      case 5 -> System.err.println(
          "Invalid input, the departure time cannot be before the current time. Try again.");
      case 6 ->
          System.err.println("There is already a departure with that train number, try a new one.");
      case 7 -> System.err.println(
          "Invalid input, the input has to be an integer between 1 and 2. Try again.");
      case 8 -> System.err.println("There is no departure with that train number.");
      case 9 ->
          System.err.println("There is already a train with the same line and departure time");
      case 10 -> System.err.println("Try again.");
      case 11 ->
          System.err.println("There is already a train with the same track and departure time.");
      case 12 -> System.err.println("Something went wrong.");
      default -> System.err.println("Choice out of bounds");
    }

  }

  /**
   * The {@code printChoiceError} method prints an error message to the user, informing the user
   * that the input has to be an integer between the given minimum and maximum value.
   *
   * @param min The minimum value of the choice.
   * @param max The maximum value of the choice.
   */
  public void printChoiceError(int min, int max) {
    System.err.println("Invalid input, the number has to be between " + min + " and " + max);
  }


  /**
   * The {@code printGeneralDeparture} method prints the header for the general departure table.
   *
   * @param currentTime The current time of the program.
   */
  public void printGeneralDeparture(LocalTime currentTime) {
    System.out.println("|Departure Time|  |Line|  |Number|  |Destination|  |Track|  "
        + currentTime);
    printLine();
  }

  /**
   * The {@code printLine} method prints a line to the console.
   */
  public void printLine() {
    System.out.println("-----------------------------------------------------------------");
  }

  /**
   * The {@code printWhiteSpace} method prints a white space to the console.
   */
  public void printWhiteSpace() {
    System.out.println();
  }

  /**
   * The {@code printEndMessage} method prints the end message to the user.
   */
  public void printEndMessage() {
    printLine();
    System.out.println("Thank you for using the train dispatch application!");
    System.out.println("The application will now shut down.");
  }
}

