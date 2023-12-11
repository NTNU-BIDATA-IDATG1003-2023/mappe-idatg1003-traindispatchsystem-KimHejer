package edu.ntnu.stud;

import edu.ntnu.stud.traindeparturegui.TrainDepartureGUI;

/**
 * This is the main class for the train dispatch application.
 *
 *
 * @author Kim Hejer
 * @version 1.1.1
 * @since 1.0.0
 */
public class TrainDispatchApp {


  /**
   * The main method for the train dispatch application.
   *
   * <p>The main method creates a new {@code TrainDepartureGUI} object and calls the {@code init()}
   * and {@code start()} methods.
   *
   * @param args The command line arguments.
   * @since 1.0.0
   */
  public static void main(String[] args) {
    TrainDepartureGUI trainDepartureGUI = new TrainDepartureGUI();
    trainDepartureGUI.init();
    trainDepartureGUI.start();

  }

  public TrainDispatchApp() {
    // Overriding default constructor
  }
}





