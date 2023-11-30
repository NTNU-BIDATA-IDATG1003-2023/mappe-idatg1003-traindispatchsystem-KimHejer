package edu.ntnu.stud.traindeparturetools;

import edu.ntnu.stud.traindepartures.TrainDeparture;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Printer {

  private String timeStringFormat = "The format has to be HH:MM, for example 12:00";

  public void printDepartureDetails(TrainDeparture trainDeparture){
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

  public void printMenuChoices(){
    System.out.println("What would you like to do? Enter a number between 1 and 9.");
    System.out.println("1. Add a new train departure");
    System.out.println("2. Change a train departure");
    System.out.println("3. Add delay to a train departure");
    System.out.println("4. Delete a train departure");
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


  public void printWelcomeMessage() {
    System.out.println("Welcome to the train dispatch application!");
  }

  public void printTimeStringFormat() {
    System.out.println(timeStringFormat);
  }

  public void printErrorMessage(int choice) {
    switch(choice) {
      case 0:
        break;
      case 1:
        System.out.println("Invalid input, the input has to be an integer. Try again.");
        break;
      case 2:
        System.out.println("Invalid input, the input has to be an integer between 1 and 9. Try again.");
        break;
      case 3:
        System.out.println("The value cannot be empty or null. Try again.");
        break;
      case 4:
        System.out.println("Invalid input, try again");
        break;
      case 5:
        System.out.println("Invalid input, the departure time cannot be before the current time. Try again.");
        break;
      case 6:
        System.out.println("There is already a departure with that train number, try a new one.");
        break;
      case 7:
        System.out.println("Invalid input, the input has to be an integer between 1 and 2. Try again.");

    }

  }
  public void printGeneralDeparture() {
    System.out.println("|Departure Time|  |Number|  |Line|  |Destination|  |Track|");
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
}
