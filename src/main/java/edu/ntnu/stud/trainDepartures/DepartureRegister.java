package edu.ntnu.stud.trainDepartures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalTime;
import java.util.TreeMap;

public class DepartureRegister {

  HashMap<Integer, TrainDeparture> departureHashMap;
  TreeMap<String, TrainDeparture> departureTreeMap;
  // Iterator<TrainDeparture> departureIterator;
  public DepartureRegister() {
    departureHashMap = new HashMap<>();
    departureTreeMap = new TreeMap<>();
  }

  public TrainDeparture getDepartureByTrainNumber(int trainNumber) {
    TrainDeparture output = null;
    if (departureHashMap.containsKey(trainNumber)) {
      output = departureHashMap.get(trainNumber);
    }
    return output;
  }


  public Iterator<TrainDeparture> getDeparturesByDestination(String destination) {
    return departureHashMap.values()
        .stream()
        .filter(departure -> departure.getDestination().equals(destination))
        .sorted(Comparator.comparing(departure -> departure.getDepartureTime())).iterator();
  }

  /*
  public Iterator<TrainDeparture> findDeparturesByDestination2(String destination) {
    Iterator<TrainDeparture> outputIterator = sortDeparturesByDepartureTime();

    return outputIterator;
  }
  */

  public Iterator<TrainDeparture> getAllDeparturesSorted() {
    return departureHashMap.values()
        .stream()
        .sorted(Comparator.comparing(departure -> LocalTime.parse(departure.getDepartureTime())))
        .iterator();
  }

  public Iterator<TrainDeparture> sortDeparturesByDepartureTime2(HashMap<Integer, TrainDeparture> listToBeSorted) {
    return listToBeSorted.values()
        .stream()
        .sorted((departure1, departure2) -> LocalTime.parse(departure1.getDepartureTime())
            .compareTo(LocalTime.parse(departure2.getDepartureTime())))
        .iterator();
  }
  public void sortDepartures(){
    Iterator<TrainDeparture> test = departureHashMap.values().iterator();
    while(test.hasNext()) {
      departureTreeMap.put(test.next().getDepartureTime(), test.next());
    }
  }


  public Iterator<TrainDeparture> getAllDepartures() {
    return departureHashMap.values().iterator();
  }
  public void removeDeparturesBeforeCurrentTime(LocalTime currentTime) {
    Iterator<TrainDeparture> departureIterator =  departureHashMap.values()
        .stream()
        .filter(departure -> LocalTime.parse(departure.getDepartureTime())
            .isBefore(currentTime)).iterator();

    while(departureIterator.hasNext()) {
      TrainDeparture temp = departureIterator.next();
      departureHashMap.remove(temp.getTrainNumber());
      departureTreeMap.remove(temp.getDepartureTime());
    }
  }

  public boolean addNewTrainDeparture(TrainDeparture trainDeparture) {
    boolean output = false;
    if(getDepartureByTrainNumber(trainDeparture.getTrainNumber()) == null) {
      output = true;
      departureHashMap.put(trainDeparture.getTrainNumber(), trainDeparture);
      departureTreeMap.put(trainDeparture.getDepartureTime(), trainDeparture);
    }
    return output;
  }



}
