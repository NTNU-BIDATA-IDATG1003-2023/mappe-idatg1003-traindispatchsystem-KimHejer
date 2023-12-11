[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/sT7H9ZJB)
# Portfolio project IDATA1003 - 2023

STUDENT NAME = Kim Hejer  
STUDENT ID = 562964

## Project description

The train dispatch app is a simplified system for dispatching train departures. The system applies a text-based user interface in the console. Instructions are given throughout the project, giving the user the ability to execute commands by entering inputs in the console. If the given input is invalid, the system will give an error message, followed by new instructions.


## Project structure

The project is structured by folders. The main application and tests can be found under the folder "src".
To locate the main application "TrainDispatchApp", follow the folders "main" -> "java" -> "edu.stud.ntnu".
The test classes are found following the folders "test" -> "java" -> ed.stud.ntnu.traindepartures". 
There is also a log file containing error messages that would crash the program, located in the file "error_log.txt"

## Link to repository

https://github.com/NTNU-BIDATA-IDATG1003-2023/mappe-idatg1003-traindispatchsystem-KimHejer

## Requirements

* Java (JDK 8+)

## How to run the project

To run the file, open the class "TrainDispatchApp" and run the "main" method or create an instance of "TrainDepartureGUI" and call its "init" and "start" methods. The program will then display a welcome message and further give instructions to the user. The user executes commands by typing numbers with corresponding methods in the console. In the occurance of invalid inputs, the program will display an error message with new instructions. 

## How to run the tests
<p></p>
With Maven:
<p>In the Maven menu, open the folder "Lifecycle" and click on "Run Maven Build" under "test".
</p>
Without Maven:
<p>First locate the classes "TrainDepartureTest" and "DepartureRegister". The tests can be run one method at a time by clicking the run icon next to the method, or all the methods at once by running the test class itself. 

## References

https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger
