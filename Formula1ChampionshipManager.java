package com.f1.race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{

    List<Driver> drivers = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    private int uniqueId;
    DefaultTableModel model ;

    public Formula1ChampionshipManager(){
        uniqueId = 1;
        readFromFile();
    }

    public void f1RaceSimulatorGUI(){

        updateTableData();
        JTable table = new JTable(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        JFrame frame = new JFrame("F1 Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBounds(0,0,650,650);
        mainPanel.add(tablePane);

        JPanel buttonPane = new JPanel();
        buttonPane.setBounds(650,10, 150, 650);
        JButton btnAddRace = new JButton("Add Race");
        btnAddRace.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRace();
                updateTableData();
                model.fireTableDataChanged();
                table.setModel(model);
                saveToFile();
            }
        });
        buttonPane.add(btnAddRace);

        JButton btnAddRacePriority = new JButton("Add Priority Race");
        btnAddRacePriority.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPriorityNewRace();
                updateTableData();
                model.fireTableDataChanged();
                table.setModel(model);
                saveToFile();
            }
        });
        buttonPane.add(btnAddRacePriority);

        mainPanel.add(buttonPane);
        frame.add(mainPanel);
        //frame.pack();
        frame.setSize(800,650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void updateTableData(){
        model = new DefaultTableModel(new String[]{"Driver Name", "Location", "Team", "Total Race", "1st Wins", "2nd Wins", "3rd Wins", "Total Points"}, 0);
        for(Driver driver : drivers){
            Formula1Driver driver1 =(Formula1Driver) driver;
            model.addRow(new Object[]{driver1.getDriverName(),
                    driver1.getLocation(), driver1.getTeam(),
                    driver1.getNoOfRaceParticipated(), driver1.getNoOf1stPositions(),
                    driver1.getNoOf2ndPositions(), driver1.getNoOf3rdPositions(),
                    driver1.getTotalPoints()});
        }
    }

    public void consoleBaseF1Menu(){

        boolean isExit = false;
        while(!isExit) {

            System.out.println("\n\n====== Formula 1 Championship ======");
            System.out.println("--- Menu ---");
            System.out.println("1. Create new Driver");
            System.out.println("2. Delete Driver");
            System.out.println("3. Change Driver Team");
            System.out.println("4. Driver Statistics");
            System.out.println("5. Table of Drivers");
            System.out.println("6. Add Race");
            System.out.println("7. GUI Simulator");
            System.out.println("8. Exit");
            System.out.println("Please enter your choice :");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    createNewDriver();
                    break;
                case 2:
                    deleteDriver();
                    break;
                case 3:
                    changeDriverTeam();
                    break;
                case 4:
                    driverStatistics();
                    break;
                case 5:
                    tableOfDrivers();
                    break;
                case 6:
                    addNewRace();
                    break;
                case 7:
                    f1RaceSimulatorGUI();
                    break;
                case 8:
                    System.out.println("Thank you !!!");
                    isExit = true;
                default:
                    System.out.println("Invalid selection..");
            }
        }

        saveToFile();

    }

    public void saveToFile() {

        try {
            FileOutputStream f = new FileOutputStream(new File("DriverDataFile.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(drivers);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initializing stream");
        }
    }

    private void readFromFile() {

        try {

            FileInputStream fi = new FileInputStream(new File("DriverDataFile.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            drivers = (List<Driver>) oi.readObject();

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Error ClassNotFoundException");
        }
    }

    private void addNewRace() {
        Date raceDate = new Date();
        System.out.println("New Race on "+new SimpleDateFormat("dd-MMM-yyyy").format(raceDate));

        Random positionSelector = new Random();
        for (Driver driver : drivers) {
            Formula1Driver f1Driver = (Formula1Driver) driver;
            int position = positionSelector.nextInt(drivers.size()) + 1;
            f1Driver.driverWinRaceAtPosition(position);
            f1Driver.setNoOfRaceParticipated(f1Driver.getNoOfRaceParticipated()+1);
        }
    }

    private void addPriorityNewRace() {
        Date raceDate = new Date();
        System.out.println("New Race on "+new SimpleDateFormat("dd-MMM-yyyy").format(raceDate));

        for (Driver driver : drivers) {
            Formula1Driver f1Driver = (Formula1Driver) driver;
            int position = getPriorityPosition(f1Driver);
            f1Driver.driverWinRaceAtPosition(position);
            f1Driver.setNoOfRaceParticipated(f1Driver.getNoOfRaceParticipated()+1);
        }
    }

    private int getPriorityPosition(Formula1Driver f1Driver){
        Random positionSelector = new Random();
        int position = 0;
        if(f1Driver.getTotalPoints() > 100) {
            position = positionSelector.nextInt(3);
        }else if(f1Driver.getTotalPoints() >50 && f1Driver.getTotalPoints() < 100){
            position = positionSelector.nextInt(6);
        }else{
            position = positionSelector.nextInt(drivers.size())+ 1;
        }
        return position;
    }


    private void tableOfDrivers() {
        System.out.println("--- Table of Drivers ---");
        System.out.println("\nName\t\tLocation\t\tTeam\t\tParticipated\t\tTotal Points");
        int count = 1;
        for (Driver driver : drivers){
            Formula1Driver f1Driver = (Formula1Driver) driver;
            System.out.println(f1Driver.getDriverName()
                    +"\t\t"+f1Driver.getLocation()+"\t\t"+f1Driver.getTeam()
                    +"\t\t"+f1Driver.getNoOfRaceParticipated()+"\t\t"+f1Driver.getTotalPoints());
            count++;
        }
    }
    
    private void driverStatistics() {

        System.out.println("--- Driver for Statistics ---");
        int count = 1;
        for (Driver driver : drivers){
            System.out.println(count+". "+driver.getDriverName());
            count++;
        }
        System.out.println("Select Driver number for Statistics");
        int driverNo = input.nextInt();

        Formula1Driver selected = (Formula1Driver) drivers.get(driverNo-1);

        System.out.println("Driver Name : "+selected.getDriverName()+"\nNo of Race Participated: "+ selected.getNoOfRaceParticipated()
                +"\nNo Wins at 1st Position: "+selected.getNoOf1stPositions()
                +"\nNo Wins at 2nd Position: "+selected.getNoOf2ndPositions()
                +"\nNo Wins at 3rd Position: "+selected.getNoOf3rdPositions()
                +"\nTotal Points: "+selected.getTotalPoints());

    }

    private void changeDriverTeam() {

        System.out.println("--- Change Driver Team ---");
        int count = 1;
        for (Driver driver : drivers){
            System.out.println(count+". "+driver.getDriverName());
            count++;
        }
        System.out.println("Select Driver number to change");
        int driverNo = input.nextInt();

        try {
            Driver selected = drivers.get(driverNo - 1);

            System.out.println("Enter Team name");
            String newTeam = input.next();
            selected.setTeam(newTeam);

        }catch (Exception e){
            System.out.println("Invalid driver number selected..");
        }
    }

    private void deleteDriver() {

        System.out.println("--- Delete Driver Menu ---");
        int count = 1;
        for (Driver driver : drivers){
            System.out.println(count+". "+driver.getDriverName());
            count++;
        }
        System.out.println("Select Driver number to Delete");
        int driverNo = input.nextInt();

        try {
            Driver deleted = drivers.remove(driverNo - 1);
            System.out.println("Driver " + deleted.getDriverName() + " is deleted");
        }catch (Exception e){
            System.out.println("Invalid driver number selected..");
        }

    }

    private void createNewDriver(){
        Formula1Driver driver = new Formula1Driver();

        System.out.println("Creating new Driver:");
        System.out.print("Enter Driver Name:");
        String name = input.next();
        System.out.print("Enter Driver Location:");
        String location = input.next();
        System.out.print("Enter Driver Team:");
        String team = input.next();
        System.out.print("No of Participated Race:");
        int noOfParticipated = input.nextInt();

        for(int i=0; i<noOfParticipated; i++){
            System.out.print("Enter Position of in Race "+i+": ");
            int position = input.nextInt();
            driver.driverWinRaceAtPosition(position);
        }

        driver.setDriverId("F1D_"+(uniqueId++));
        driver.setDriverName(name);
        driver.setLocation(location);
        driver.setTeam(team);
        driver.setNoOfRaceParticipated(noOfParticipated);

        drivers.add(driver);
    }
}
