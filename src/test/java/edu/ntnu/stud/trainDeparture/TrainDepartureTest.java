package edu.ntnu.stud.trainDeparture;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.trainDepartures.TrainDeparture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {

  private TrainDeparture trainDepartureTest;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void setTrainNumberPositiveTestForCorrectTrainNumber() {
    trainDepartureTest = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
    assertEquals(123, trainDepartureTest.getTrainNumber(), "The function is working as intended");
  }

  @Test
  void setDestinationPositiveTestForCorrectDestination() {
    trainDepartureTest = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
    assertEquals("Gjøvik", trainDepartureTest.getDestination(), "The function is working as intended");
  }

  @Test
  void setDepartureTimePositiveTestForCorrectDP() {
    trainDepartureTest = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
    assertEquals("12:00", trainDepartureTest.getDepartureTime(), "The function is working as intended");
  }

  @Test
  void setTrainNumberNegativeTestForIncorrectTrainNumber() {
    trainDepartureTest = new TrainDeparture(-5, "Gjøvik", "L5", "12:00");
    assertNotEquals(-5, trainDepartureTest.getTrainNumber(), "The function is working as intended");
  }

  @Test
  void setDestinationNegativeTestForIncorrectDestination() {
    trainDepartureTest = new TrainDeparture(123, null, "L5", "12:00");
    assertNotEquals("Oslo", trainDepartureTest.getDestination(), "The function is working as intended");
  }
  @Test
  void setDepartureTimeNegativeTestForIncorrectDP() {
    trainDepartureTest = new TrainDeparture(123, "Gjøvik", "L5", "10:L5");
    assertNotEquals("10:L5", trainDepartureTest.getDepartureTime(), "The function is working as intended");
  }

  @Test
  void setDepartureTimeNegativeTestForIncorrectDP2() {
    trainDepartureTest = new TrainDeparture(123, "Gjøvik", "L5", "50:00");
    assertNotEquals("50:00", trainDepartureTest.getDepartureTime(), "The function is working as intended");
  }

  @Test
  void setDepartureTimeNegativeTestForIncorrectDP3() {
    trainDepartureTest = new TrainDeparture(123, "Gjøvik", "L5", "010:23");
    assertNotEquals("010:23", trainDepartureTest.getDepartureTime(), "The function is working as intended");
  }
}
