package edu.ntnu.stud;

import static java.lang.String.valueOf;

import edu.ntnu.stud.traindepartures.*;
import edu.ntnu.stud.traindeparturetools.InputHandler;
import edu.ntnu.stud.traindeparturetools.Printer;
import java.io.InvalidObjectException;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the main class for the train dispatch application.
 *
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
  // Sjekke om det allerede er et tog i registeret med samme spor, tid og linje
  // Burde sjekke dette hver gang man endrer DP eller spor, og når man legger til tog, bør de
  // ikke kunne ha samma linje og tid eller samme spor og tid
  // Sjekke om det er samme linje og tid || samme tid og spor

  // Burde ha en default konstruktør i tillegg til den som tar inn parametere, søk opp hvorfor
  // Bruk dummy-verdier og sjekk gjennom objektet i GUI for å se om noe er feil og endre deretter
  // Switch case for meldinger jeg bruker ofte, men ikke for spesifikke meldinger
  // Bruk err istedenfor out på error message
  // Inputhandler bør sjekke gjennom for feilverdier, ikke appen eller gui
  // Sende logger exceptions til en annen fil, slik at det blir mulig for utviklere å lese

  // Logger logger = Logget.getLogger(TrainDispatchApp.class.getName());

  // TrainDeparture test = new TrainDeparture();
  // test.logger.log(Level.INFO, "Melding");
  // test.logger.log(Level.SEVERE, "Object creation failed")  // Ikke til bruker, men til utvikler
  private InputHandler inputHandler;
  private LocalTime currentTime = LocalTime.parse("00:00");
  private Printer printer;
  private DepartureRegister departureRegister;

  public static void main(String[] args) {
    TrainDispatchApp trainDispatchApp = new TrainDispatchApp();
    trainDispatchApp.start();

  }
  private void start() {
    // Starter prosjektet, kaller init() og viser GUI
    init();
    int menuChoice = 0;
    while (menuChoice != 9) {
      printer.printMenuChoices();
      printer.printWhiteSpace();
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
          setTrackToDeparture();
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
    printer.printWelcomeMessage();
    setCurrentTime();
    setupInitialDepartures();

  }

  private void end(){
    // Avslutter prosjektet
  }

  private void setupInitialDepartures() {
    Random rand = new Random();
    ArrayList<String> destinationArrayList = new ArrayList<>(
        List.of("Oslo",
            "Bergen",
            "Trondheim",
            "Lillehammer",
            "Holmestrand"));
    List<String> lineList = List.of("L4",
        "R4",
        "RE12",
        "F5",
        "L5");
    for (int i = 0; i < 5; i++) {
      int trainNumber = rand.nextInt(1000);
      String destination = destinationArrayList.get(i);
      String line = lineList.get(i);
      LocalTime departureTime;
      String departureTimeString;
      do {
        int intHour = rand.nextInt(23);
        int intMinute = rand.nextInt(59);
        String hour = valueOf(intHour);
        String minute = valueOf(intMinute);
        if(intHour<10) {
          hour = "0" + hour;
        }
        if(intMinute<10) {
          minute = "0" + minute;
        }
        departureTimeString = hour + ":" + minute;
        departureTime = LocalTime.parse(departureTimeString);
      } while (departureTime.isBefore(currentTime));
      TrainDeparture trainDeparture = new TrainDeparture(trainNumber, destination, line, departureTimeString);
      trainDeparture.setTrack(i+1);
      departureRegister.addNewTrainDeparture(trainDeparture);

    }
  }
  private void addNewTrainDeparture(){
    printer.printMessage("What is the train number?");
    int trainNumber = inputHandler.intInputHandlerPositive();
    String departureTime = inputHandler.setTimeString("When is the train departing?");
    while (currentTime.isAfter(LocalTime.parse(departureTime))
        && !LocalTime.parse(departureTime).equals(currentTime)) {
      printer.printErrorMessage(5);
      departureTime = inputHandler.setTimeString("When is the train departing?");
    }
    String destination = inputHandler.stringInputHandler("What is the destination of the train?");
    String line = inputHandler.stringInputHandler("What is the line of the train?");
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, destination, line, departureTime);
    while (departureRegister.addNewTrainDeparture(trainDeparture) == 0) {
      printer.printErrorMessage(6);
      trainNumber = inputHandler.intInputHandlerPositive();
      trainDeparture.changeTrainNumber(trainNumber);
    }
    while (departureRegister.addNewTrainDeparture(trainDeparture) == 2) {
      printer.printErrorMessage("There is already a train with the same line and departure time");
      // LEGGE TID CHOICE OM Å BYTTE LINE ELLER TID
    }
    printer.printMessage("The train departure has been added!");
  }

  private void searchDepartures() {
    printer.printSearchMenuChoices();
    int menuChoice;
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
    } while (menuChoice != 1 && menuChoice != 2);
  }

  private void setDelayToDeparture() {
    TrainDeparture departure = departureRegister.getDepartureByTrainNumber(getTrainNumber());
    String delayString = inputHandler.setDelayString(departure.getDepartureTime(), currentTime);
    departure.setDelay(delayString);
    // DENNE MÅ FIKSES PÅ, GIR UT MINUTTER UTEN 0 FORAN
  }

  private int getTrainNumber() {
    printer.printMessage("What is the train number?");
    return inputHandler.intInputHandlerPositive();
  }

  private void searchDepartureByTrainNumber() {
    TrainDeparture trainDeparture = getDepartureByTrainNumber();
    if (trainDeparture != null) {
      printer.printDepartureDetails(trainDeparture);
    } else {
      printer.printMessage("There is no departure with that train number.");
    }
  }
  private TrainDeparture getDepartureByTrainNumber() {
    int trainNumber = getTrainNumber();
    return departureRegister.getDepartureByTrainNumber(trainNumber);
  }

  private void searchDeparturesByDestination() {
    String destination = inputHandler.stringInputHandler("What is the destination of the departure(s)?");
    Iterator<TrainDeparture> destinationIterator = departureRegister.getDeparturesByDestination(destination);
    if (destinationIterator.hasNext()) {
      while (destinationIterator.hasNext()) {
        printer.printDepartureDetails(destinationIterator.next());
      }
    } else {
      printer.printMessage("There are no departures going to that destination.");
    }
  }

  private void setCurrentTime() {
    LocalTime newTime =  LocalTime.parse(inputHandler.setTimeString("What is the current time?"));
    while (newTime.isBefore(currentTime)) {
      printer.printMessage("You cannot set a time earlier than the current time.");
      newTime =  LocalTime.parse(inputHandler.setTimeString("What is the current time?"));
    }
    currentTime = newTime;
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
    printer.printWhiteSpace();
  }

  private void setTrackToDeparture() {
    TrainDeparture traindeparture = getDepartureByTrainNumber();
    int track;
    if (traindeparture == null) {
      printer.printMessage("There is no departure with that train number. There are 10 tracks");
    } else {
      printer.printMessage("What track will you set the departure to?");
      track = inputHandler.intInputHandlerPositive();
      while(track > 10 || track <= 0) {
        printer.printErrorMessage("The track can only be between 1 and 10");
        printer.printMessage("What track will you set the departure to?");
        track = inputHandler.intInputHandlerPositive();
      }
      traindeparture.setTrack(track);
    }
  }


}





