package edu.ntnu.stud;

import edu.ntnu.stud.trainDepartures.TrainDeparture;

public class Printer {

  private String timeStringFormat = "The format has to be HH:MM, for example 12:00";

  public void printDepartureDetails(TrainDeparture trainDeparture){
    StringBuilder departureDetails = new StringBuilder();
    departureDetails.append("############################################\n");
    if (!trainDeparture.getDelay().equals("00:00")) {
      departureDetails.append("Initial departure: " + trainDeparture.getInitialDepartureTime()
          + " , Current departure: "
          + trainDeparture.getDepartureTime());
    } else {
      departureDetails.append("Departure: " + trainDeparture.getDepartureTime());
    }
    departureDetails.append("\nLine: " + trainDeparture.getLine())
            .append("\nTrain number: " + trainDeparture.getTrainNumber())
            .append("\nDestination: " + trainDeparture.getDestination());
    if (trainDeparture.getTrack() != 0) {
      departureDetails.append("\nTrack: " + trainDeparture.getTrack());
    } else {
      departureDetails.append("\nTrack: Not set");
    }
    System.out.println(departureDetails);
  }

  public void printMenuChoices(){
    System.out.println("What would you like to do? Enter a number between 1 and 7.");
    System.out.println("1. Add a new train departure");
    System.out.println("2. Change a train departure");
    System.out.println("3. Add delay to a train departure");
    System.out.println("4. Delete a train departure");
    System.out.println("5. Search for a train departure");
    System.out.println("6. Print all train departures");
    System.out.println("9. Exit");
    // Legge til et tog
    // Endre på en togavgang
    // Slette en togavgang
    // Søke etter en togavgang
    // Skrive ut alle togavganger
    // Avslutte programmet
  }


  public void printWelcomeMessage() {
    System.out.println("Welcome to the train dispatch application!");
  }

  public void printTimeStringFormat() {
    System.out.println(timeStringFormat);
  }

  public void printErrorMessage(int choice) {
    switch(choice) {
      case 1:
        System.out.println("Invalid input, the input has to be an integer. Try again.");
        break;
      case 2:
        System.out.println("Invalid input, the input has to be an integer between 1 and 7. Try again.");
        break;
      case 3:
        System.out.println("The value cannot be empty or null. Try again.");
        break;
      case 4:
        System.out.println("Invalid input, try again");
        System.out.println(timeStringFormat);

    }

  }

  public void printMessage(String message) {
    System.out.println(message);
  }

}
