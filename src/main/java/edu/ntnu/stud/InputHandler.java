package edu.ntnu.stud;

import edu.ntnu.stud.trainDepartures.Validator;
import java.util.Scanner;

public class InputHandler {

  private Scanner userInput;
  private Validator validator;
  private Printer printer;
  private String timeStringFormat = "The format has to be HH:MM, for example 12:00";

  public InputHandler() {
    userInput = new Scanner(System.in);
    validator = new Validator();
    printer = new Printer();
  }

  private int intInputHandler2() {
    int menuChoice = 0;
    try {
      menuChoice = userInput.nextInt();
    } catch (Exception e) {
      System.out.println("Invalid input, the input has to be an integer.");
      intInputHandler2();
    }
    userInput.nextLine();
    return menuChoice;
  }

  public int intInputHandler() {
    String menuChoice = userInput.nextLine();
    int output = 0;
    try {
      output = Integer.parseInt(menuChoice);
    } catch (Exception e) {
      // System.out.println(e.getCause());
      // System.out.println(e.getMessage());
      printer.printErrorMessage(1);
      intInputHandler();
    }
    return output;
  }

  public int choiceHandler(int min, int max) {
    int output = intInputHandler();
    if (output < min || output > max) {
      printer.printMessage("Invalid input, the number has to be between " + min + " and " + max);
      choiceHandler(min, max);
    }
    return output;
  }

  public String stringInputHandler(String message) {
    printer.printMessage(message);
    String menuChoice = userInput.nextLine();
    while (!validator.validateString(menuChoice)) {
      printer.printErrorMessage(3);
      printer.printMessage(message);
      menuChoice = userInput.nextLine();
    }
    return menuChoice;
  }

  public String setTimeString(String message) {
    printer.printMessage(message);
    String timeString = stringInputHandler(timeStringFormat);
    while (!validator.checkTimeString(timeString)) {
      printer.printErrorMessage(4);
      printer.printMessage(message);
      printer.printMessage(timeStringFormat);
      timeString = userInput.nextLine();
    }
    return timeString;
  }

  public String setDelayString(String departureTime, Object currentTime) {
    String delayString;
    do {
      delayString = setTimeString("How much time would you like to delay the departure with?");
    } while(validator.validateDelay(departureTime, delayString, currentTime));
    return delayString;
  }
}
