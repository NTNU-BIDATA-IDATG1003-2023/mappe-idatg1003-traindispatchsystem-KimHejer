package edu.ntnu.stud;

import edu.ntnu.stud.trainDepartures.*;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * This is the main class for the train dispatch application.
 *
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.

  private Scanner userInput;
  private InputHandler inputHandler;
  private LocalTime currentTime;
  private Validator validator;
  private Printer printer;
  private String timeStringFormat = "The format has to be HH:MM, for example 12:00";

  public static void main(String[] args) {
    TrainDispatchApp trainDispatchApp = new TrainDispatchApp();
    trainDispatchApp.start();

  }
  private void start() {
    // Starter prosjektet, kaller init() og viser GUI
    init();
    printer.printWelcomeMessage();
    setTimeString("What is the current time?");
    int menuChoice = 0;
    while (menuChoice != 9) {
      printer.printMenuChoices();
      menuChoice = inputHandler.intInputHandler();
      switch (menuChoice) {
        case 1:
          addNewTrainDeparture();
          break;
        case 2:
          // changeTrainDeparture();
          break;
        case 3:
          // add delay to train departure
          // Legg inn forsinkelse på en togavgang – ved å først søke etter en gitt togavgang basert på
          // tognummer, og deretter legge til forsinkelse
          break;
        case 4:
          // Tildele spor til en togavgang – ved først å søke opp togavgang basert på tognummer, og så
          //sette spor.
          break;
        case 5:
          // searchTrainDepartureTrainNumber();
          // Søke etter en togavgang basert på Tognummer
          break;
        case 6:
          // printAllTrainDepartures();
          break;
        case 7:
          // Søke etter togavgang basert på destinasjon
          // searchTrainDepartureDestination();
          break;
        case 8:
          // Oppdatere klokken (tidspunktet på dagen) – ved å spørre bruker etter nytt klokkeslett.
          // setCurrentTime();
          break;
        case 9:
          end();
          break;
        default:
          System.out.println("Invalid input, try again");
          break;
      }
    }
  }

  private void init(){
    // Initialiserer prosjektet, oppretter nødvendige objekter og setter opp GUI
    userInput = new Scanner(System.in);
    validator = new Validator();
    printer = new Printer();
    inputHandler = new InputHandler();

  }
  private String stringInputHandler(String message) {
    System.out.println(message);
    String menuChoice = userInput.nextLine();
    while (!validator.validateString(menuChoice)){
      System.out.println("The value cannot be empty or null.");
      System.out.println(message);
      menuChoice = userInput.nextLine();
    }
    return menuChoice;
  }

  private String setTimeString(String message) {
    System.out.println(message);
    String timeString = stringInputHandler(timeStringFormat);
    while (!validator.checkTimeString(timeString)){
      System.out.println("Invalid input, try again");
      System.out.println(timeStringFormat);
      timeString = userInput.nextLine();
  }
    return timeString;
  }
  private void end(){
    // Avslutter prosjektet
  }
  private void addNewTrainDeparture(){
    String departureTime = setTimeString("When is the train departing?");
    String destination = inputHandler.stringInputHandler("What is the destination of the train?");
    String line = inputHandler.stringInputHandler("What is the line of the train?");
    System.out.println("What is the train number?");
    int trainNumber = inputHandler.intInputHandler();
    TrainDeparture newTrainDeparture =
        new TrainDeparture(trainNumber, destination, line, departureTime);
    System.out.println("The train departure has been added!");
  }

}


