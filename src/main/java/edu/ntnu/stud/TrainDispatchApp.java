package edu.ntnu.stud;

import edu.ntnu.stud.traindeparturegui.TrainDepartureGUI;

/**
 * This is the main class for the train dispatch application.
 *
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
  // Vise current time når nye tider skal gis
  // Burde ha en default konstruktør i tillegg til den som tar inn parametere, søk opp hvorfor
  // Bruk dummy-verdier og sjekk gjennom objektet i GUI for å se om noe er feil og endre deretter
  // Inputhandler bør sjekke gjennom for feilverdier, ikke appen eller gui
  // Sende logger exceptions til en annen fil, slik at det blir mulig for utviklere å lese
  // Trenger enhetstest-klasse til registerklassen SE DEL 2 AV MAPPEVURDERING
  // Hvis train number er under 1000, legg til null(er) foran i print
  // Muligens flytte all kode herifra til GUI og heller opprette en instans av den og kalle på start og init

  // Logger logger = Logget.getLogger(TrainDispatchApp.class.getName());
  // TrainDeparture test = new TrainDeparture();
  // test.logger.log(Level.INFO, "Melding");
  // test.logger.log(Level.SEVERE, "Object creation failed")  // Ikke til bruker, men til utvikler

  public static void main(String[] args) {
    TrainDepartureGUI trainDepartureGUI = new TrainDepartureGUI();
    trainDepartureGUI.init();
    trainDepartureGUI.start();

  }
  public TrainDispatchApp() {

  }
}





