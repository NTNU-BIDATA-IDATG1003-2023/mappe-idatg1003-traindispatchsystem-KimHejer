package edu.ntnu.stud.traindepartures;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartureRegisterTest {

  DepartureRegister departureRegister;
  TrainDeparture trainDeparture1;
  TrainDeparture trainDeparture2;
  TrainDeparture trainDeparture3;
  TrainDeparture trainDeparture4;
  TrainDeparture trainDeparture5;
  TrainDeparture trainDeparture6;
  Iterator<TrainDeparture> sortedDepartureIterator;
  boolean test;

  @BeforeEach
  void setUp() {
    departureRegister = new DepartureRegister();
    trainDeparture1 =
        new TrainDeparture(124, "Oslo", "L5", "14:10");
    trainDeparture2 =
        new TrainDeparture(123, "Gjøvik", "L4", "12:00");
    trainDeparture3 =
        new TrainDeparture(125, "Trondheim", "L6", "16:20");
    trainDeparture4 =
        new TrainDeparture(126, "Bergen", "L7", "18:30");
    trainDeparture5 =
        new TrainDeparture(124, "Holmestrand", "G9", "14:15");
    trainDeparture6 =
        new TrainDeparture(127, "Oslo", "L5", "14:10");

  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getDepartureByTrainNumberPositiveTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    assertEquals(trainDeparture1, departureRegister.getDepartureByTrainNumber(124),
        "getDepartureByTrainNumber is not returning the correct train departure");
  }

  @Test
  void getAllDeparturesSortedPositiveTest() {

    departureRegister.addNewTrainDeparture(trainDeparture1);
    departureRegister.addNewTrainDeparture(trainDeparture2);
    departureRegister.addNewTrainDeparture(trainDeparture3);
    departureRegister.addNewTrainDeparture(trainDeparture4);
    Iterator<TrainDeparture> sortedDepartureIterator = departureRegister.getAllDeparturesSorted();
    assertEquals(trainDeparture2, sortedDepartureIterator.next(),
        "getAllDeparturesSorted is not sorting correctly");
    assertEquals(trainDeparture1, sortedDepartureIterator.next(),
        "getAllDeparturesSorted is not sorting correctly");
    assertEquals(trainDeparture3, sortedDepartureIterator.next(),
        "getAllDeparturesSorted is not sorting correctly");
    assertEquals(trainDeparture4, sortedDepartureIterator.next(),
        "getAllDeparturesSorted is not sorting correctly");

  }

  @Test
  void removeDeparturesBeforeCurrentTime() {
    LocalTime currentTime = LocalTime.parse("14:00");
    departureRegister.addNewTrainDeparture(trainDeparture1);
    departureRegister.addNewTrainDeparture(trainDeparture2);
    departureRegister.addNewTrainDeparture(trainDeparture3);
    departureRegister.removeDeparturesBeforeCurrentTime(currentTime);
    sortedDepartureIterator = departureRegister.getAllDeparturesSorted();
    assertEquals(trainDeparture1, sortedDepartureIterator.next(),
        "removeDeparturesBeforeCurrentTime is not removing correctly");
    assertEquals(trainDeparture3, sortedDepartureIterator.next(),
        "removeDeparturesBeforeCurrentTime is not removing correctly");
  }

  @Test
  void checkTrackAndTimePositiveTest() {
    trainDeparture1.setTrack(1);
    trainDeparture2.setTrack(2);
    departureRegister.addNewTrainDeparture(trainDeparture1);
    test = departureRegister.checkTrackAndTime(trainDeparture2.getTrack(),
        trainDeparture2.getDepartureTime());
    assertFalse(test, "checkTrackAndTime is not working correctly");

  }

  @Test
  void checkLineAndTimePositiveTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    test = departureRegister.checkLineAndTime(trainDeparture2.getLine(),
        trainDeparture2.getDepartureTime());
    assertFalse(test, "checkLineAndTime is not working correctly");
  }

  /*
  @Test
  void addNewTrainDeparturePositiveTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    departureRegister.addNewTrainDeparture(trainDeparture2);
    departureRegister.addNewTrainDeparture(trainDeparture3);
    departureRegister.addNewTrainDeparture(trainDeparture4);
    sortedDepartureIterator = departureRegister.getAllDeparturesSorted();
    int i = 0;
    while (sortedDepartureIterator.hasNext()) {
      i++;
    }
    assertEquals(4, i, "addNewTrainDeparture is not adding correctly");
  }

   */

  @Test
  void updateHashMapPositiveTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    int initialTrainNumber = trainDeparture1.getTrainNumber();
    trainDeparture1.changeTrainNumber(130);
    departureRegister.updateHashMap(trainDeparture1, initialTrainNumber);
    assertEquals(trainDeparture1, departureRegister.getDepartureByTrainNumber(130),
        "updateHashMap is not updating correctly");
  }

  @Test
  void getDeparturesByDestinationPositiveTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    departureRegister.addNewTrainDeparture(trainDeparture2);
    departureRegister.addNewTrainDeparture(trainDeparture3);
    departureRegister.addNewTrainDeparture(trainDeparture4);
    Iterator<TrainDeparture> destinationIterator = departureRegister
        .getDeparturesByDestination("Oslo");
    assertEquals(trainDeparture1, destinationIterator.next(),
        "getDeparturesByDestination is not returning the correct train departure");
  }

  @Test
  void updateHashMapNegativeTest() {
    int initialTrainNumber = trainDeparture1.getTrainNumber();
    trainDeparture1.changeTrainNumber(140);
    departureRegister.updateHashMap(trainDeparture1, initialTrainNumber);
    assertNotEquals(trainDeparture1, departureRegister.getDepartureByTrainNumber(130),
        "updateHashMap is not updating correctly");
  }

  @Test
  void getDepartureByTrainNumberNegativeTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    departureRegister.addNewTrainDeparture(trainDeparture5);
    assertNotEquals(trainDeparture5, departureRegister.getDepartureByTrainNumber(124),
        "getDepartureByTrainNumber is not returning the correct train departure");
  }
  /*
  @Test
  void addNewTrainDepartureNegativeTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    departureRegister.addNewTrainDeparture(trainDeparture2);
    departureRegister.addNewTrainDeparture(trainDeparture3);
    departureRegister.addNewTrainDeparture(trainDeparture5);
    sortedDepartureIterator = departureRegister.getAllDeparturesSorted();
    int i = 0;
    while (sortedDepartureIterator.hasNext()) {
      i++;
    }
    assertEquals(3, i, "addNewTrainDeparture is not adding correctly");
  }

   */

  @Test
  void checkTrackAndTimeNegativeTest() {
    trainDeparture1.setTrack(1);
    trainDeparture6.setTrack(1);
    departureRegister.addNewTrainDeparture(trainDeparture1);
    test = departureRegister.checkTrackAndTime(trainDeparture6.getTrack(),
        trainDeparture6.getDepartureTime());
    assertTrue(test, "checkTrackAndTime is not working correctly");

  }

  @Test
  void checkLineAndTimeNegativeTest() {
    departureRegister.addNewTrainDeparture(trainDeparture1);
    test = departureRegister.checkLineAndTime(trainDeparture6.getLine(),
        trainDeparture6.getDepartureTime());
    assertTrue(test, "checkLineAndTime is not working correctly");
  }
}