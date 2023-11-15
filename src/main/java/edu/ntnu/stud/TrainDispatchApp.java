package edu.ntnu.stud;

import edu.ntnu.stud.trainDepartures.*;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This is the main class for the train dispatch application.
 *
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.


  private InputHandler inputHandler;
  private LocalTime currentTime;
  private Printer printer;
  private DepartureRegister departureRegister;
  private String timeStringFormat = "The format has to be HH:MM, for example 12:00";

  public static void main(String[] args) {
    TrainDispatchApp trainDispatchApp = new TrainDispatchApp();
    trainDispatchApp.start();

  }
  private void start() {
    // Starter prosjektet, kaller init() og viser GUI
    init();
    printer.printWelcomeMessage();
    setCurrentTime();
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
          printAllTrainDepartures();
          break;
        case 7:
          // Søke etter togavgang basert på destinasjon
          // searchDeparturesByDestination();
          break;
        case 8:
          // Oppdatere klokken (tidspunktet på dagen) – ved å spørre bruker etter nytt klokkeslett.
          setCurrentTime();
          break;
        case 9:
          end();
          break;
        default:
          printer.printErrorMessage(2);
          break;
      }
    }
  }

  private void init(){
    // Initialiserer prosjektet, oppretter nødvendige objekter og setter opp GUI
    printer = new Printer();
    inputHandler = new InputHandler();
    departureRegister = new DepartureRegister();

  }

  private void end(){
    // Avslutter prosjektet
  }
  private void addNewTrainDeparture(){
    printer.printMessage("What is the train number?");
    int trainNumber = inputHandler.intInputHandler();
    String departureTime = inputHandler.setTimeString("When is the train departing?");
    while (currentTime.isAfter(LocalTime.parse(departureTime))
        && !LocalTime.parse(departureTime).equals(currentTime)) {
      printer.printErrorMessage(5);
      departureTime = inputHandler.setTimeString("When is the train departing?");
    }
    String destination = inputHandler.stringInputHandler("What is the destination of the train?");
    String line = inputHandler.stringInputHandler("What is the line of the train?");
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, destination, line, departureTime);
    while (!departureRegister.addNewTrainDeparture(trainDeparture)) {
      printer.printErrorMessage(6);
      trainNumber = inputHandler.intInputHandler();
      trainDeparture.changeTrainNumber(trainNumber);
    }
    printer.printMessage("The train departure has been added!");
  }

  private void setCurrentTime() {
    currentTime =  LocalTime.parse(inputHandler.setTimeString("What is the current time?"));
    departureRegister.removeDeparturesBeforeCurrentTime(currentTime);
  }

  private void printAllTrainDepartures() {
    Iterator allDepartures = departureRegister.getDepartureArray();
    while (allDepartures.hasNext()) {
      TrainDeparture trainDeparture = (TrainDeparture) allDepartures.next();
      printer.printDepartureDetails(trainDeparture);
    }
  }

}

// Compare to
// Avanserte samlinger som sorterer for deg


