package edu.ntnu.stud.trainDeparture;
import java.time.LocalTime;

// LocalTime.parse("06:30") Gjør tiden til 06:30
// LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS) legger til timer,
// finnes også for spesifikt tillegg eller fradrag (.minus()) av timer eller minutter
// LocalTime.parse(tid)getHour() henter timen, samme finnes for minutter
// LocalTime.parse(tid).isBefore(LocalTime.parse(annenTid) boolean verdi
public class TrainDeparture {
  private int trainNumber;
  private String track;
  private String destination;
  private String line;
  private String departureTime;
  private String delay = "00:00";

  public TrainDeparture(int trainNumber, String destination, String line,
                        String departureTime){
    this.setTrainNumber(trainNumber);
    this.setDestination(destination);
    this.setLine(line);
    this.setDepartureTime(departureTime);
  }

  private void setTrainNumber(int trainNumber) {
    this.trainNumber = Math.max(trainNumber, 0);
  }

  private int getTrainNumber() {
    return trainNumber;
  }

  private void setTrack(String track){
    this.track = track;
  }

  public String getTrack() {
    return track;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getDestination() {
    return destination;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public void setDepartureTime(String departureTime) {
    if (checkTimeString(departureTime)){
      this.departureTime = departureTime;
    } else{
      this.departureTime = "INVALID";
    }
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void addDelay(String delay){
    if(checkTimeString(delay)){
      this.delay = delay;
    } else{
      this.delay = "INVALID";
    }
  }

  public boolean checkTimeString(String timeString) {
    String[] timeStringList = timeString.split("\\:", 0);
    boolean output = true;
    if (timeStringList.length != 2) {
      output = false;
    } else {
      String hour = timeStringList[0];
      String minute = timeStringList[1];
      try {
        Integer.parseInt(hour);
        Integer.parseInt(minute);
      } catch (Exception e) {
        output = false;
      }
    }
    return output;
  }
  private int[] stringToTime(String timeString){
    String[] timeStringList = timeString.split("\\:");
    int hour = Integer.parseInt(timeStringList[0]);
    int minute = Integer.parseInt(timeStringList[1]);
    return new int[]{hour, minute};
  }

  private String timeToString(int hour, int minute){
    return hour + "" + minute;
  }
}
