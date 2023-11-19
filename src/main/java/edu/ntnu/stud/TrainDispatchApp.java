package edu.ntnu.stud;

import edu.ntnu.stud.trainDepartures.*;
import java.time.LocalTime;
import java.util.Iterator;

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
      menuChoice = inputHandler.choiceHandler(1, 9);
      switch (menuChoice) {
        case 1:
          addNewTrainDeparture();
          break;
        case 2:
          // changeTrainDeparture();
          break;
        case 3:
          setDelayToDeparture();
          // add delay to train departure
          // Legg inn forsinkelse på en togavgang – ved å først søke etter en gitt togavgang basert på
          // tognummer, og deretter legge til forsinkelse
          break;
        case 4:
          // Tildele spor til en togavgang – ved først å søke opp togavgang basert på tognummer, og så
          //sette spor.
          break;
        case 5:
          searchDepartures();
          // Søke etter en togavgang basert på Tognummer
          break;
        case 6:
          printAllTrainDepartures();
          break;
        case 7:
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

  private void searchDepartures() {
    printer.printSearchMenuChoices();
    int menuChoice = 0;
    do {
    menuChoice = inputHandler.choiceHandler(1, 2);
    switch (menuChoice) {
      case 1:
        searchDepartureByTrainNumber();
        break;
      case 2:
        searchDeparturesByDestination();
        break;
      default:
        printer.printErrorMessage(7);
        break;
    }
    } while (menuChoice != 1 && menuChoice != 2 && menuChoice != 3);
  }

  private void setDelayToDeparture() {
    String departureTime = departureRegister.getDepartureByTrainNumber(getTrainNumber()).getDepartureTime();
    inputHandler.setDelayString(departureTime, currentTime);
    // DENNE MÅ FIKSES PÅ
  }

  private int getTrainNumber() {
    printer.printMessage("What is the train number?");
    return inputHandler.intInputHandler();
  }

  private void searchDepartureByTrainNumber() {
    int trainNumber = getTrainNumber();
    TrainDeparture trainDeparture = departureRegister.getDepartureByTrainNumber(trainNumber);
    if (trainDeparture != null) {
      printer.printDepartureDetails(trainDeparture);
    } else {
      printer.printMessage("There is no departure with that train number.");
    }
  }

  private void searchDeparturesByDestination() {
    String destination = inputHandler.stringInputHandler("What is the destination of the departure(s)?");
    Iterator<TrainDeparture> test = departureRegister.getDeparturesByDestination(destination);
    if (test.hasNext()) {
      while (test.hasNext()) {
        printer.printDepartureDetails(test.next());
      }
    } else {
      printer.printMessage("There are no departures going to that destination.");
    }
  }

  private void setCurrentTime() {
    currentTime =  LocalTime.parse(inputHandler.setTimeString("What is the current time?"));
    departureRegister.removeDeparturesBeforeCurrentTime(currentTime);
    printer.printMessage("The time is now: " + currentTime);
  }

  private void printAllTrainDepartures() {
    Iterator<TrainDeparture> allDepartures = departureRegister.getAllDeparturesSorted();
    if (allDepartures.hasNext()) {
      printer.printMessage("Printing all departures:");
      printer.printGeneralDeparture();
      while (allDepartures.hasNext()) {
        TrainDeparture trainDeparture = allDepartures.next();
        printer.printDepartureDetails(trainDeparture);
      }
    } else {
      printer.printMessage("There are no departures at the moment.");
    }
  }

  private void printAllTrainDeparturesSorted() {
    Iterator allDepartures = departureRegister.getAllDeparturesSorted();
    while (allDepartures.hasNext()) {
      TrainDeparture trainDeparture = (TrainDeparture) allDepartures.next();
      printer.printDepartureDetails(trainDeparture);
    }
  }

}

// Compare to
// Avanserte samlinger som sorterer for deg


