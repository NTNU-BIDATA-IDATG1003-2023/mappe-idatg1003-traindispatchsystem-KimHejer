package edu.ntnu.stud.traindeparturegui;

import static java.lang.String.valueOf;

import edu.ntnu.stud.traindepartures.*;
import edu.ntnu.stud.traindeparturetools.InputHandler;
import edu.ntnu.stud.traindeparturetools.Printer;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The {@code TrainDepartureGUI} class is a Graphical User Interface (GUI) for the Train Dispatch
 * System. The class contains methods for initializing and starting the GUI, and handling user
 * input. It uses the {@code InputHandler} to handle user input, which then is uses to create and
 * alter {@code TrainDeparture} objects in the {@code DepartureRegister}. The class also uses the
 * {@code Printer} class to print messages to the user.
 *
 * @author Kim Hejer
 * @version 1.0.0
 * @since 1.0.0
 * @see InputHandler
 * @see Printer
 * @see DepartureRegister
 * @see TrainDeparture
 */
public class TrainDepartureGUI {

  private InputHandler inputHandler;
  private LocalTime currentTime = LocalTime.parse("00:00");
  private Printer printer;
  private DepartureRegister departureRegister;
  private int menuChoice;
  private Random rand;


  /**
   * The {@code start} method starts the main menu in the GUI.
   *
   * <p></p> The menu contains methods for adding, editing and searching for train departures.
   *
   */
  public void start() {
    while (menuChoice != 8) {
      printer.printMenuChoices();
      printer.printWhiteSpace();
      menuChoice = inputHandler.choiceHandler(1, 8);
      switch (menuChoice) {
        case 1 -> addNewTrainDeparture();
        case 2 -> editTrainDeparture();
        case 3 -> setDelayToDeparture(); // Denne må endres, om du skriver feil tid så vil den ikke si ifra, bare gå i loop
        // Legg inn forsinkelse på en togavgang – ved å først søke etter en gitt togavgang basert på
        // tognummer, og deretter legge til forsinkelse
        case 4 -> setTrackToDeparture(getTrainNumber());

        // Tildele spor til en togavgang – ved først å søke opp togavgang basert på tognummer, og så
        //sette spor.
        case 5 -> searchDepartures();

        // Søke etter en togavgang basert på Tognummer
        case 6 -> printAllTrainDepartures();
        case 7 -> setCurrentTime();
        case 8 -> end();
        default -> printer.printErrorMessage(2);
      }
    }
  }

  /**
   * The {@code init} method initializes the necessary objects required to start the GUI.
   *
   */
  public void init(){
    printer = new Printer();
    inputHandler = new InputHandler();
    departureRegister = new DepartureRegister();
    rand = new Random();
    printer.printWelcomeMessage();
    setCurrentTime();
    setupInitialDepartures();

  }

  /**
   * The {@code end} method prints a message to the user signaling the end of the program.
   */
  public void end(){
    printer.printEndMessage();
  }

  private void setupInitialDepartures() {
    List<String> destinationList = List.of("Oslo",
        "Bergen",
        "Trondheim",
        "Lillehammer",
        "Holmestrand");
    List<String> lineList = List.of("L4",
        "R4",
        "RE12",
        "F5",
        "L5");
    for (int i = 0; i < 5; i++) {
      int trainNumber = rand.nextInt(1000);
      String destination = destinationList.get(i);
      String line = lineList.get(i);
      LocalTime departureTime;
      String departureTimeString;
      do {
        int intHour = (int)Math.floor(Math.random() *(23 - currentTime.getHour() + 1) + currentTime.getHour());
        int intMinute = rand.nextInt(60);
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
    printer.printMessage(1);
    int trainNumber = inputHandler.intInputHandlerPositive();
    String departureTime = inputHandler.departureTimeStringMaker(currentTime);
    String destination = inputHandler.stringInputHandler("What is the destination of the train?");
    String line = inputHandler.stringInputHandler("What is the line of the train?");
    TrainDeparture trainDeparture = new TrainDeparture(trainNumber, destination, line, departureTime);

    while (!departureRegister.addNewTrainDeparture(trainDeparture)) {
      trainNumber = validateDepartureByTrainNumber(trainNumber);
      trainDeparture.changeTrainNumber(trainNumber);
      while (departureRegister.checkLineAndTime(trainDeparture.getLine(), trainDeparture.getDepartureTime())) {
        printer.printErrorMessage(9);
        printer.printMessage("We can fix this by changing the departure time.");
        changeDepartureTime(trainDeparture);
      }
    }
    printer.printMessage("The train departure has been added!");
  }

  private void editTrainDeparture() {
    TrainDeparture trainDeparture = getDepartureByTrainNumber();
    if (searchDepartureByTrainNumber(trainDeparture)) {
      printer.printChangeMenuChoices();
      menuChoice = inputHandler.choiceHandler(1, 4);
      switch (menuChoice) {
        case 1 -> changeTrainNumber(trainDeparture);
        case 2 -> changeLine(trainDeparture);
        case 3 -> changeDestination(trainDeparture);
        case 4 -> changeDepartureTime(trainDeparture);
        default -> printer.printErrorMessage(2);
      }
    }
  }

  private void changeTrainNumber(TrainDeparture trainDeparture) {
    int initialTrainNumber = trainDeparture.getTrainNumber();
    printer.printMessage("What would you like to change the train number to?");
    int trainNumber = inputHandler.intInputHandlerPositive();
    trainNumber = validateDepartureByTrainNumber(trainNumber);
    trainDeparture.changeTrainNumber(trainNumber);
    departureRegister.updateHashMap(trainDeparture, initialTrainNumber);
    printer.printMessage("The new train number is now: " + trainDeparture.getTrainNumber());
  }

  private void changeLine(TrainDeparture trainDeparture) {
    String line = inputHandler.stringInputHandler("What would you like to change the line to?");
    if (departureRegister.checkLineAndTime(line.toUpperCase(), trainDeparture.getDepartureTime())) {
      printer.printErrorMessage(9);
      printer.printErrorMessage(10);
      changeLine(trainDeparture);
    } else {
      trainDeparture.changeLine(line);
      printer.printMessage("The new line is now: " + trainDeparture.getLine());
    }
  }

  private void changeDestination(TrainDeparture trainDeparture) {
    String destination = inputHandler.stringInputHandler("What would you like the new destination to be?");
    trainDeparture.changeDestination(destination);
    printer.printMessage("The new destination is now: " + trainDeparture.getDestination());
  }

  private void changeDepartureTime(TrainDeparture trainDeparture) {
    String departureTime;
    do {
      departureTime = inputHandler.departureTimeStringMaker(currentTime);
      if (departureRegister.checkLineAndTime(trainDeparture.getLine(),
          departureTime)) {
        printer.printErrorMessage(9);
        printer.printErrorMessage(10);
      }
      if (departureRegister.checkTrackAndTime(trainDeparture.getTrack(), departureTime)) {
        printer.printErrorMessage(11);
        printer.printErrorMessage(10);
      }
    } while (departureRegister.checkLineAndTime(trainDeparture.getLine(), departureTime)
        || departureRegister.checkTrackAndTime(trainDeparture.getTrack(), departureTime));
    trainDeparture.changeDepartureTime(departureTime);
    printer.printMessage("The train is now departing at: " + trainDeparture.getDepartureTime());
  }

  private int validateDepartureByTrainNumber(int trainNumber) {
    while (departureRegister.getDepartureByTrainNumber(trainNumber) != null) {
      printer.printErrorMessage(6);
      trainNumber = inputHandler.intInputHandlerPositive();
    }
    return trainNumber;
  }

  private void validateDeparture(TrainDeparture trainDeparture) {
    String errorValue = "INVALID";
    if (trainDeparture.getTrainNumber() == 0) {
      changeTrainNumber(trainDeparture);
    }
    if (trainDeparture.getDestination().equals(errorValue)) {
      changeDestination(trainDeparture);
    }
    if (trainDeparture.getLine().equals(errorValue)) {
      changeLine(trainDeparture);
    }
    if (trainDeparture.getDepartureTime().equals(errorValue)) {
      changeDepartureTime(trainDeparture);
    }
    if (trainDeparture.getTrack() == -1) {
      setTrackToDeparture(trainDeparture.getTrainNumber());
    }
  }

  private void searchDepartures() {
    printer.printSearchMenuChoices();
    do {
      menuChoice = inputHandler.choiceHandler(1, 2);
      switch (menuChoice) {
        case 1 -> searchDepartureByTrainNumber(getDepartureByTrainNumber());
        case 2 -> searchDeparturesByDestination();
        default -> printer.printErrorMessage(7);
      }
    } while (menuChoice != 1 && menuChoice != 2);
  }

  private void setDelayToDeparture() {
    TrainDeparture trainDeparture = departureRegister.getDepartureByTrainNumber(getTrainNumber());
    if (trainDeparture != null) {
      String delayString = inputHandler.setDelayString(trainDeparture.getDepartureTime(),
          currentTime);
      LocalTime newTime = LocalTime.parse(delayString)
          .plusHours(LocalTime.parse(trainDeparture.getDepartureTime()).getHour())
          .plusMinutes(LocalTime.parse(trainDeparture.getDepartureTime()).getMinute());
      String newTimeString = trainDeparture.localTimeToString(newTime);
      if (departureRegister.checkLineAndTime(trainDeparture.getLine(),
          newTimeString)) {
        printer.printErrorMessage(9);
      } else if (departureRegister.checkTrackAndTime(trainDeparture.getTrack(), newTimeString)) {
        printer.printErrorMessage(11);
      } else {
        trainDeparture.setDelay(delayString);
      }
    } else {
      printer.printErrorMessage(8);
    }
  }

  private int getTrainNumber() {
    printer.printMessage(1);
    return inputHandler.intInputHandlerPositive();
  }

  private boolean searchDepartureByTrainNumber(TrainDeparture trainDeparture) {
    boolean output = false;
    if (trainDeparture != null) {
      printer.printGeneralDeparture(currentTime);
      printer.printDepartureDetails(trainDeparture);
      output = true;
    } else {
      printer.printErrorMessage(8);
    }
    return output;
  }
  private TrainDeparture getDepartureByTrainNumber() {
    int trainNumber = getTrainNumber();
    return departureRegister.getDepartureByTrainNumber(trainNumber);
  }

  private void searchDeparturesByDestination() {
    String destination = inputHandler.stringInputHandler("What is the destination of the departure(s)?");
    Iterator<TrainDeparture> destinationIterator = departureRegister.getDeparturesByDestination(destination);
    if (destinationIterator.hasNext()) {
      printer.printGeneralDeparture(currentTime);
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
      printer.printErrorMessage("You cannot set a time earlier than the current time.");
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
      printer.printGeneralDeparture(currentTime);
      while (allDepartures.hasNext()) {
        TrainDeparture trainDeparture = allDepartures.next();
        printer.printDepartureDetails(trainDeparture);
      }
    } else {
      printer.printMessage("There are no departures at the moment.");
    }
    printer.printWhiteSpace();
  }

  private void setTrackToDeparture(int trainNumber) {
    TrainDeparture traindeparture = departureRegister.getDepartureByTrainNumber(trainNumber);
    int track;
    if (traindeparture == null) {
      printer.printErrorMessage(8);
    } else {
      printer.printMessage("What track will you set the departure to? There are 10 tracks.");
      track = inputHandler.intInputHandlerPositive();
      while(track > 10 || track <= 0) {
        printer.printErrorMessage("The track can only be between 1 and 10");
        printer.printMessage("What track will you set the departure to? There are 10 tracks.");
        track = inputHandler.intInputHandlerPositive();
      }
      if (departureRegister.checkTrackAndTime(track, traindeparture.getDepartureTime())) {
        printer.printErrorMessage(11);
        printer.printErrorMessage(10);
        setTrackToDeparture(trainNumber);
      } else {
        traindeparture.setTrack(track);
      }
    }
  }
}
