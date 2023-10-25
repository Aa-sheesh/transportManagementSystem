import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Abstract class for vehicles
abstract class Vehicle {
    protected String vehicleNumber;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public abstract void addPassenger(Passenger passenger);
    public abstract void displayPassengers();
}

// Interface for handling file operations
interface FileHandler {
    void saveToFile(String fileName) throws IOException;
    void loadFromFile(String fileName) throws IOException;
}

// Class to represent a bus
class Bus extends Vehicle {
    private ArrayList<Passenger> passengers;

    public Bus(String vehicleNumber) {
        super(vehicleNumber);
        passengers = new ArrayList<>();
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    @Override
    public void displayPassengers() {
        System.out.println("Passengers on Bus " + vehicleNumber + ":");
        for (Passenger passenger : passengers) {
            System.out.println(passenger.getName() + " (ID: " + passenger.getId() + ")");
        }
    }
}

// Class to represent a passenger
class Passenger {
    private String name;
    private int id;

    public Passenger(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

public class TransportManagementSystem {
    private ArrayList<Vehicle> vehicles;

    public TransportManagementSystem() {
        vehicles = new ArrayList<>();
    }

    // Add a bus to the system
    public void addBus(String vehicleNumber) {
        vehicles.add(new Bus(vehicleNumber));
        System.out.println("Bus " + vehicleNumber + " added to the system.");
    }

    // Add a passenger to a bus
    public void addPassengerToBus(String vehicleNumber, Passenger passenger) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.vehicleNumber.equals(vehicleNumber) && vehicle instanceof Bus) {
                vehicle.addPassenger(passenger);
                System.out.println(passenger.getName() + " (ID: " + passenger.getId() + ") added to Bus " + vehicleNumber + ".");
                return;
            }
        }
        System.out.println("Bus " + vehicleNumber + " not found in the system.");
    }

    // Display passengers on a bus
    public void displayPassengersOnBus(String vehicleNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.vehicleNumber.equals(vehicleNumber) && vehicle instanceof Bus) {
                vehicle.displayPassengers();
                return;
            }
        }
        System.out.println("Bus " + vehicleNumber + " not found in the system.");
    }

    public static void main(String[] args) {
        TransportManagementSystem transportSystem = new TransportManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int passengerId = 1;

        while (true) {
            System.out.println("\nTransport Management System Menu:");
            System.out.println("1. Add a Bus");
            System.out.println("2. Add a Passenger to a Bus");
            System.out.println("3. Display Passengers on a Bus");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Bus Number: ");
                    String busNumber = scanner.next();
                    transportSystem.addBus(busNumber);
                    break;

                case 2:
                    System.out.print("Enter Passenger Name: ");
                    String name = scanner.next();
                    Passenger passenger = new Passenger(name, passengerId);
                    passengerId++;

                    System.out.print("Enter Bus Number to Add the Passenger: ");
                    String selectedBusNumber = scanner.next();
                    transportSystem.addPassengerToBus(selectedBusNumber, passenger);
                    break;

                case 3:
                    System.out.print("Enter Bus Number to Display Passengers: ");
                    String displayBusNumber = scanner.next();
                    transportSystem.displayPassengersOnBus(displayBusNumber);
                    break;

                case 4:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
