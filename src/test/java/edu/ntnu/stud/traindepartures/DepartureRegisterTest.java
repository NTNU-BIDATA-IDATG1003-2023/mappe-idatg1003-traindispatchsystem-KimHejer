package edu.ntnu.stud.traindepartures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartureRegisterTest {

  @BeforeEach
  void setUp() {
    TrainDeparture trainDeparture1 = new TrainDeparture(100, "oslo", "l5", "12:00");
    TrainDeparture trainDeparture2 = new TrainDeparture(101, "gjøvik", "f5", "12:10");
    TrainDeparture trainDeparture3 = new TrainDeparture(100, "oslo", "l5", "12:00");
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getDepartureByTrainNumber() {
  }

  @Test
  void getAllDeparturesSorted() {
  }

  @Test
  void removeDeparturesBeforeCurrentTime() {
  }

  @Test
  void checkTrackAndTime() {
  }

  @Test
  void checkLineAndTime() {
  }

  @Test
  void addNewTrainDeparture() {
  }
}