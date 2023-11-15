package edu.ntnu.stud.trainDepartureGUIs;

import edu.ntnu.stud.trainDepartures.TrainDeparture;
import java.time.LocalTime;

public class TrainDepartureGUI {

  public static void printDepartureDetails(TrainDeparture trainDeparture){
    System.out.println("############################################");
    if (!trainDeparture.getDelay().equals("00:00")) {
      System.out.println("Initial departure: " + trainDeparture.getInitialDepartureTime()
                         + " , Current departure: "
                         + trainDeparture.getDepartureTime());
    } else {
      System.out.println("Departure: " + trainDeparture.getDepartureTime());    }
    System.out.println("Line: " + trainDeparture.getLine());
    System.out.println("Train number: " + trainDeparture.getTrainNumber());
    System.out.println("Destination: " + trainDeparture.getDestination());
    if (trainDeparture.getTrack() != 0) {
      System.out.println("Track: " + trainDeparture.getTrack());
    }
  }

  public static void main(String[] args) {
    TrainDeparture test1 = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
    TrainDeparture test2 = new TrainDeparture(124, "Hamar", "L6", "12:05");

    test2.setDelay("00:05");

    printDepartureDetails(test1);
    printDepartureDetails(test2);

    LocalTime time1 = LocalTime.parse(test1.getDepartureTime());
    LocalTime time2 = LocalTime.parse(test2.getDelay());
    time1 = time1.plusHours(time2.getHour());
    time1 = time1.plusMinutes(time2.getMinute());
    System.out.println(time1);
  }

}
