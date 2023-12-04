package edu.ntnu.stud.traindeparturetools;

import edu.ntnu.stud.traindepartures.TrainDeparture;
import java.util.stream.Collectors;

public class Printer {

  private StringBuilder stringBuilder;

  public Printer() {
    stringBuilder = new StringBuilder();
  }

  public void printDepartureDetails(TrainDeparture trainDeparture) {
    System.out.print(" ");
    if (!trainDeparture.getDelay().equals("00:00")) {
      System.out.format("%-5s", strikeThrough(trainDeparture.getInitialDepartureTime()));
      System.out.format("%3s", "");
      System.out.format("%-10s", trainDeparture.getDepartureTime());
    } else {
      System.out.format("%-18s", trainDeparture.getDepartureTime());
    }
    System.out.format("%-10s", trainDeparture.getTrainNumber());
    System.out.format("%-8s", trainDeparture.getLine());
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
        .mapToObj(c -> "" + (char) c + "\u0336")
        .collect(Collectors.joining());
  }

  public void printMenuChoices() {
    System.out.println("What would you like to do? Enter a number between 1 and 9.");
    System.out.println("1. Add a new train departure");
    System.out.println("2. Change a train departure");
    System.out.println("3. Add delay to a train departure");
    System.out.println("4. Assign track to departure");
    System.out.println("5. Search for a train departure");
    System.out.println("6. Print all train departures");
    System.out.println("7. Change time");
    System.out.println("8. None");
    System.out.println("9. Exit");
  }

  public void printSearchMenuChoices() {
    System.out.println("What would you like to do? Enter a number between 1 and 2.");
    System.out.println("1. Search for departure by train number.");
    System.out.println("2. Search for departures with the same destination.");
  }

  public void printChangeMenuChoices() {
    resetStringBuilder();
    stringBuilder.append("What would yo like to edit? Enter a number between 1 and 4." + "\n")
        .append("1. Change Train Number" + "\n")
        .append("2. Change Line" + "\n")
        .append("3. Change Destination" + "\n")
        .append("4. Change Departure Time");
    System.out.println(stringBuilder);
  }

  private void resetStringBuilder() {
    if (!stringBuilder.isEmpty()) {
      stringBuilder.setLength(0);
    }
  }

  public void printWelcomeMessage() {
    System.out.println("Welcome to the train dispatch application!");
  }

  public void printTimeStringFormat() {
    String timeStringFormat = "The format has to be HH:MM, for example 12:00";
    System.out.println(timeStringFormat);
  }

  public void printMessage(int choice) {
    switch (choice) {
      case 1 -> System.out.println("What is the train number?");
      case 2 -> System.out.println("When is the train departing?");
      default -> System.err.println("Choice out of bounds");
    }
  }

  public void printChoiceError(int min, int max) {
    System.err.println("Invalid input, the number has to be between " + min + " and " + max);
  }

  public void printErrorMessage(int choice) {
    switch (choice) {
      case 1 -> System.err.println("Invalid input, the input has to be an integer. Try again.");
      case 2 -> System.err.println(
          "Invalid input, the input has to be an integer between 1 and 9. Try again.");
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
      default -> System.err.println("Choice out of bounds");
    }

  }

  public void printGeneralDeparture() {
    System.out.println("|Departure Time|  |Number|  |Line|  |Destination|  |Track|");
    printLine();
  }

  public void printLine() {
    System.out.println("----------------------------------------------------------");
  }

  public void printMessage(String message) {
    System.out.println(message);
  }

  public void printErrorMessage(String message) {
    System.err.println(message);
  }

  public void printWhiteSpace() {
    System.out.println();
  }

  public void printEndMessage() {
    printLine();
    System.out.println("Thank you for using the train dispatch application!");
    System.out.println("The application will now shut down.");
  }
}
