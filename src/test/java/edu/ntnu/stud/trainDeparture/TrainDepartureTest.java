package edu.ntnu.stud.trainDeparture;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.traindepartures.TrainDeparture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainDepartureTest {

  private TrainDeparture trainDeparturePositive;
  private TrainDeparture trainDepartureNegative;

  @BeforeEach
  void setUp() {
    trainDeparturePositive = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
    trainDepartureNegative = new TrainDeparture(123, "Gjøvik", "L5", "12:00");
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void setTrainNumberPositiveTestForCorrectTrainNumber() {
    assertEquals(123, trainDeparturePositive.getTrainNumber(), "The function is working as intended");
  }

  @Test
  void setDestinationPositiveTestForCorrectDestination() {
    assertEquals("Gjøvik".toUpperCase(), trainDeparturePositive.getDestination(), "The function is working as intended");
  }

  @Test
  void setDepartureTimePositiveTestForCorrectDP() {
    assertEquals("12:00", trainDeparturePositive.getDepartureTime(), "The function is working as intended");
  }

  @Test
  void setTrainNumberNegativeTestForIncorrectTrainNumber() {
    trainDepartureNegative.setTrainNumber(-5);
    assertNotEquals(-5, trainDepartureNegative.getTrainNumber(), "The function is working as intended");
  }

  @Test
  void setDestinationNegativeTestForIncorrectDestination() {
    trainDepartureNegative.setDestination("Gjøvik");
    assertNotEquals("Oslo", trainDeparturePositive.getDestination(), "The function is working as intended");
  }
  @Test
  void setDepartureTimeNegativeTestForIncorrectDP() {
    trainDepartureNegative.setDepartureTime("10:L5");
    assertNotEquals("10:L5", trainDeparturePositive.getDepartureTime(), "The function is working as intended");
  }

  @Test
  void setDepartureTimeNegativeTestForIncorrectDP2() {
    trainDeparturePositive = new TrainDeparture(123, "Gjøvik", "L5", "50:00");
    assertNotEquals("50:00", trainDeparturePositive.getDepartureTime(), "The function is working as intended");
  }

  @Test
  void setDepartureTimeNegativeTestForIncorrectDP3() {
    trainDeparturePositive = new TrainDeparture(123, "Gjøvik", "L5", "010:23");
    assertNotEquals("010:23", trainDeparturePositive.getDepartureTime(), "The function is working as intended");
  }
}
