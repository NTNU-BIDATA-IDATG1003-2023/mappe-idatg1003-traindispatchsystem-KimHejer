package edu.ntnu.stud.trainDepartures;

import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalTime;

public class DepartureRegister {

  HashMap<Integer, TrainDeparture> departureHashMap;
  // Iterator<TrainDeparture> departureIterator;
  public DepartureRegister() {
    departureHashMap = new HashMap<>();
  }

  public TrainDeparture findDepartureByTrainNumber(int trainNumber) {
    TrainDeparture output = null;
    if (departureHashMap.containsKey(trainNumber)) {
      output = departureHashMap.get(trainNumber);
    }
    return output;
  }

  public Iterator findDeparturesByDestination(String destination) {
    Iterator<TrainDeparture> outputIterator = departureHashMap.values()
        .stream()
        .filter(departure -> departure.getDestination().equals(destination))
        .iterator();
    return outputIterator;
  }
  /*
  public int addNewTrainDeparture(int trainNumber, String destination, String line, String departureTime) {
    int output = 0;
    departureIterator = departureRegister.values().iterator();
    while (departureIterator.hasNext()) {
      TrainDeparture trainDeparture = departureIterator.next();
      if (trainNumber != trainDeparture.getTrainNumber()) {
        if (line.equals(trainDeparture.getLine())
            && departureTime.equals(trainDeparture.getDepartureTime())) {
          output = 2;
        } else {
          departureRegister.put(trainNumber, new TrainDeparture(trainNumber, destination, line, departureTime));
        }
      } else {
        output = 6;
      }
    }
    return output;
  }

   */
  public Iterator<TrainDeparture> sortDeparturesByDepartureTime() {
    return departureHashMap.values()
        .stream()
        .sorted((departure1, departure2) -> LocalTime.parse(departure1.getDepartureTime())
            .compareTo(LocalTime.parse(departure2.getDepartureTime())))
        .iterator();
  }
  public Iterator<TrainDeparture> getDepartureArray() {
    return departureHashMap.values().iterator();
  }
  public void removeDeparturesBeforeCurrentTime(LocalTime currentTime) {
    Iterator<TrainDeparture> departureIterator =  departureHashMap.values()
        .stream()
        .filter(departure -> LocalTime.parse(departure.getDepartureTime())
            .isBefore(currentTime)).iterator();

    while(departureIterator.hasNext()) {
      TrainDeparture nei = departureIterator.next();
      departureHashMap.remove(nei.getTrainNumber());
    }
  }

  public boolean addNewTrainDeparture(TrainDeparture trainDeparture) {
    boolean output = false;
    if(findDepartureByTrainNumber(trainDeparture.getTrainNumber()) == null) {
      output = true;
      departureHashMap.put(trainDeparture.getTrainNumber(), trainDeparture);
    }
    return output;
  }



}
