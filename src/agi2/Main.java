package agi2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static CalendarApp app = new CalendarApp();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Room\n2. Add Employee\n3. Book Meeting\n4. Book Vacation" +
                    "\n5. Check Availability\n6. Print Agenda\n7. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addRoom();
                case 2 -> addEmployee();
                case 3 -> bookMeeting();
                case 4 -> bookVacation();
                case 5 -> checkAvailability();
                case 6 -> printAgenda();
                case 7 -> exit();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addRoom() {
        System.out.print("Enter room name: ");
        String roomName = scanner.nextLine();
        rooms.add(new Room(roomName));
        System.out.println("Room added: " + roomName);
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String employeeName = scanner.nextLine();
        employees.add(new Employee(employeeName));
        System.out.println("Employee added: " + employeeName);
    }

    private static void bookMeeting() {
        Room room = selectRoom();
        Employee employee = selectEmployee();
        if (room == null || employee == null) return;

        System.out.print("Enter date and time for meeting (yyyy-MM-dd HH:mm): ");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (app.bookMeeting(dateTime, room, employee)) {
                System.out.println("Meeting booked successfully.");
            } else {
                System.out.println("Could not book meeting due to a conflict.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm'.");
        }
    }

    private static void bookVacation() {
        Employee employee = selectEmployee();
        if (employee == null) return;

        try {
            System.out.print("Enter vacation start date (yyyy-MM-dd): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.print("Enter vacation end date (yyyy-MM-dd): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            app.bookVacation(startDate, endDate, employee);
            System.out.println("Vacation booked successfully.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd'.");
        }
    }

    private static void checkAvailability() {
        Room room = selectRoom();
        Employee employee = selectEmployee();
        if (room == null || employee == null) return;

        System.out.print("Enter date and time to check availability (yyyy-MM-dd HH:mm): ");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (app.isAvailable(dateTime, room, employee)) {
                System.out.println("Room and employee are available.");
            } else {
                System.out.println("Room or employee is not available.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm'.");
        }
    }

    private static void printAgenda() {
        System.out.println("Select to print agenda for:\n1. Room\n2. Employee");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            Room room = selectRoom();
            if (room != null) app.printAgenda(room);
        } else if (choice == 2) {
            Employee employee = selectEmployee();
            if (employee != null) app.printAgenda(employee);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static Room selectRoom() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available. Please add a room first.");
            return null;
        }
        System.out.println("Available rooms:");
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1) + ". " + rooms.get(i).getName());
        }
        System.out.print("Select a room by number: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            return index >= 0 && index < rooms.size() ? rooms.get(index) : null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }

    private static Employee selectEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees available. Please add an employee first.");
            return null;
        }
        System.out.println("Available employees:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getName());
        }
        System.out.print("Select an employee by number: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            return index >= 0 && index < employees.size() ? employees.get(index) : null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }

    private static void exit() {
        System.out.println("Goodbye!");
        scanner.close();
        System.exit(0);
    }
}
