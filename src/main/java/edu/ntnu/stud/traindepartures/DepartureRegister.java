package edu.ntnu.stud.traindepartures;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The {@code DepartureRegister} register class for {@code TrainDeparture} objects using a
 * {@code HashMap} to store the {@code TrainDeparture} objects by their train number.
 *
 * <p>The {@code DepartureRegister} class contains methods for adding, removing and updating
 * {@code TrainDeparture} objects in the {@code HashMap}. It also contains methods for getting
 * {@code TrainDeparture} objects by their train number or destination.
 *
 * @author Kim Hejer
 * @version 1.0.0
 * @see TrainDeparture
 * @since 1.0.0
 */
public class DepartureRegister {

  HashMap<Integer, TrainDeparture> departureHashMap;
  Iterator<TrainDeparture> departureIterator;

  /**
   * The {@code DepartureRegister} constructor creates a new {@code HashMap} object.
   *
   * @since 1.0.0
   */
  public DepartureRegister() {
    departureHashMap = new HashMap<>();
  }

  /**
   * The {@code getDepartureByTrainNumber} method returns a {@code TrainDeparture} object from the
   * {@code HashMap} using the train number as key. If there is no {@code TrainDeparture} object
   * with the given train number, the method returns {@code null}.
   *
   * @param trainNumber The train number of the {@code TrainDeparture} object to be returned.
   * @return The {@code TrainDeparture} object with the given train number or null.
   * @since 1.0.0
   */
  public TrainDeparture getDepartureByTrainNumber(int trainNumber) {
    TrainDeparture output = null;
    if (departureHashMap.containsKey(trainNumber)) {
      output = departureHashMap.get(trainNumber);
    }
    return output;
  }

  /**
   * The {@code updateHashMap} method replaces an old version of a {@code TrainDeparture} object
   * from the {@code HashMap} with the new {@code TrainDeparture} object using the train number as
   * key.
   *
   * @param trainDeparture     The {@code TrainDeparture} object to be added to the
   *                           {@code HashMap}.
   * @param initialTrainNumber The previous train number of the {@code TrainDeparture}.
   * @since 1.0.0
   */
  public void updateHashMap(TrainDeparture trainDeparture, int initialTrainNumber) {
    departureHashMap.remove(initialTrainNumber);
    addNewTrainDeparture(trainDeparture);
  }

  /**
   * The {@code getDeparturesByDestination} method returns an {@code Iterator} object containing all
   * {@code TrainDeparture} objects with the given destination.
   *
   * @param destination The destination of the {@code TrainDeparture} objects to be returned.
   * @return An {@code Iterator} object containing all {@code TrainDeparture} objects with the given
  destination.
   * @since 1.0.0
   */
  public Iterator<TrainDeparture> getDeparturesByDestination(String destination) {
    return departureHashMap.values().stream()
        .filter(departure -> departure.getDestination().equalsIgnoreCase(destination))
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime)).iterator();
  }

  /**
   * The {@code getAllDeparturesSorted} method returns an {@code Iterator} object containing all
   * {@code TrainDeparture} objects in the {@code HashMap} sorted by departure time.
   *
   * @return An {@code Iterator} object containing all {@code TrainDeparture} objects in the
  {@code HashMap} sorted by departure time.
   * @since 1.0.0
   */
  public Iterator<TrainDeparture> getAllDeparturesSorted() {
    return departureHashMap.values()
        .stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .iterator();
  }

  /**
   * The {@code removeDepartureByTrainNumber} method removes all {@code TrainDeparture} objects with
   * departure time before the current time from the {@code HashMap}.
   *
   * @param currentTime The time threshold for removing {@code TrainDeparture} objects.
   * @since 1.0.0
   */
  public void removeDeparturesBeforeCurrentTime(LocalTime currentTime) {
    departureIterator = departureHashMap.values()
        .stream()
        .filter(departure -> LocalTime.parse(departure.getDepartureTime())
            .isBefore(currentTime)).iterator();

    while (departureIterator.hasNext()) {
      TrainDeparture temp = departureIterator.next();
      departureHashMap.remove(temp.getTrainNumber());
    }
  }

  /**
   * The {@code checkTrackAndTime} method checks if there already is a {@code TrainDeparture} object
   * with the same track and departure time in the {@code HashMap}.
   *
   * @param track         The track of the {@code TrainDeparture} object to be checked.
   * @param departureTime The departure time of the {@code TrainDeparture} object to be checked.
   * @return {@code true} if there already is a {@code TrainDeparture} object with the same track
    and departure time in the {@code HashMap}, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean checkTrackAndTime(int track, String departureTime) {
    boolean output = false;
    for (TrainDeparture trainDeparture : departureHashMap.values()) {
      if (trainDeparture.getTrack() == track
          && trainDeparture.getDepartureTime().equals(departureTime)) {
        output = true;
      }
    }
    return output;
  }

  /**
   * The {@code checkLineAndTime} method checks if there already is a {@code TrainDeparture} object
   * with the same line and departure time in the {@code HashMap}.
   *
   * @param line          The line of the {@code TrainDeparture} object to be checked.
   * @param departureTime The departure time of the {@code TrainDeparture} object to be checked.
   * @return {@code true} if there already is a {@code TrainDeparture} object with the same line and
    departure time in the {@code HashMap}, {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean checkLineAndTime(String line, String departureTime) {
    boolean output = false;
    for (TrainDeparture trainDeparture : departureHashMap.values()) {
      if (trainDeparture.getLine().equalsIgnoreCase(line)
          && trainDeparture.getDepartureTime().equals(departureTime)) {
        output = true;
      }
    }
    return output;
  }

  /**
   * The {@code addNewTrainDeparture} method adds a new {@code TrainDeparture} object to the
   * {@code HashMap} if there is no {@code TrainDeparture} object with the same train number or if
   * there is no {@code TrainDeparture} object with the same line and departure time.
   *
   * @param trainDeparture The {@code TrainDeparture} object to be added to the {@code HashMap}.
   * @return {@code true} if the {@code TrainDeparture} object was added to the {@code HashMap},
   {@code false} otherwise.
   * @since 1.0.0
   */
  public boolean addNewTrainDeparture(TrainDeparture trainDeparture) {
    boolean output = false;
    if (getDepartureByTrainNumber(trainDeparture.getTrainNumber()) == null
        && !checkLineAndTime(trainDeparture.getLine(), trainDeparture.getDepartureTime())) {
      output = true;
      departureHashMap.put(trainDeparture.getTrainNumber(), trainDeparture);
    }
    return output;
  }


}
