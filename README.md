# Formula 1 Race Championship Manager

## Overview

This project is a Formula 1 Race Championship Manager that allows managing driver information and simulating race results. The application is structured around a few core classes and interfaces to handle the functionalities related to drivers and race simulations.

## Structure

### Interfaces

- **ChampionshipManager**
  - `void consoleBaseF1Menu()`: Launches a console-based menu for managing the championship.
  - `void f1RaceSimulatorGUI()`: Launches a GUI-based race simulator for the championship.

### Classes

- **Driver (abstract)**
  - Implements `Serializable`.
  - Fields:
    - `String driverId`
    - `String driverName`
    - `String location`
    - `String team`
  - Methods:
    - Getters and setters for each field.

- **Formula1Driver**
  - Extends `Driver` and implements `Serializable`.
  - Additional Fields:
    - `int noOfRaceParticipated`
    - `int noOf1stPositions`
    - `int noOf2ndPositions`
    - `int noOf3rdPositions`
    - `int totalPoints`
  - Methods:
    - Constructors to initialize the fields.
    - Getters and setters for each field.
    - `void driverWinRaceAtPosition(int position)`: Updates the points and positions based on the race outcome.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- A Java IDE or a text editor
- Basic knowledge of Java programming

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/Formula1RaceChampionshipManager.git
    ```
2. Navigate to the project directory:
    ```bash
    cd Formula1RaceChampionshipManager
    ```

### Usage

1. Compile the Java files:
    ```bash
    javac -d bin src/com/f1/race/*.java
    ```
2. Run the application:
    ```bash
    java -cp bin com.f1.race.MainClass
    ```
   (Replace `MainClass` with the actual main class name if it's different.)

## Contributing

1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature-branch
    ```
3. Commit your changes:
    ```bash
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```bash
    git push origin feature-branch
    ```
5. Open a pull request.
