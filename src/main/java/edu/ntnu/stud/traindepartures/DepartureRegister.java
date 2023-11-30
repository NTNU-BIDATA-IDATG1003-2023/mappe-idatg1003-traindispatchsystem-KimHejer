package edu.ntnu.stud.traindepartures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalTime;

public class DepartureRegister {

  HashMap<Integer, TrainDeparture> departureHashMap;
  Iterator<TrainDeparture> departureIterator;
  public DepartureRegister() {
    departureHashMap = new HashMap<>();
  }

  public TrainDeparture getDepartureByTrainNumber(int trainNumber) {
    TrainDeparture output = null;
    if (departureHashMap.containsKey(trainNumber)) {
      output = departureHashMap.get(trainNumber);
    }
    return output;
  }


  public Iterator<TrainDeparture> getDeparturesByDestination(String destination) {
    return departureHashMap.values().stream()
        .filter(departure -> departure.getDestination().equals(destination))
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime)).iterator();
  }


  public Iterator<TrainDeparture> getAllDeparturesSorted() {
    return departureHashMap.values()
        .stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .iterator();
  }

  public void removeDeparturesBeforeCurrentTime(LocalTime currentTime) {
    departureIterator =  departureHashMap.values()
        .stream()
        .filter(departure -> LocalTime.parse(departure.getDepartureTime())
            .isBefore(currentTime)).iterator();

    while(departureIterator.hasNext()) {
      TrainDeparture temp = departureIterator.next();
      departureHashMap.remove(temp.getTrainNumber());
    }
  }

  public boolean checkTrackAndTime(TrainDeparture newTrainDeparture) {
    boolean output = true;
    for (TrainDeparture trainDeparture : departureHashMap.values()) {
      if (trainDeparture.getTrack() == newTrainDeparture.getTrack()
          && trainDeparture.getDepartureTime().equals(newTrainDeparture.getDepartureTime())) {
        output = false;
      }
    }
    return output;
  }

  public boolean checkLineAndTime(TrainDeparture newTrainDeparture) {
    boolean output = true;
    for (TrainDeparture trainDeparture : departureHashMap.values()) {
      if (trainDeparture.getLine().equals(newTrainDeparture.getLine())
          && trainDeparture.getDepartureTime().equals(newTrainDeparture.getDepartureTime())) {
        output = false;
      }
    }
    return output;
  }

  public int addNewTrainDeparture(TrainDeparture trainDeparture) {
    int output = 0;
    if(getDepartureByTrainNumber(trainDeparture.getTrainNumber()) == null) {
      output = 1;
      departureHashMap.put(trainDeparture.getTrainNumber(), trainDeparture);
    } else if (checkLineAndTime(trainDeparture)) {
      output = 2;
    }
    return output;
  }



}
