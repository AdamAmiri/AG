package agi2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarApp {
    private List<Booking> bookings = new ArrayList<>();

    // Book a meeting
    public boolean bookMeeting(LocalDateTime dateTime, Room room, Employee employee) {
        for (Booking booking : bookings) {
            if (booking.getType().equals("Meeting") && booking.getMeetingDateTime().equals(dateTime) &&
                (booking.getRoom().equals(room) || booking.getEmployee().equals(employee))) {
                System.out.println("Conflict detected. Meeting not booked.");
                return false;
            }
        }
        bookings.add(new Booking("Meeting", dateTime, room, employee));
        System.out.println("Meeting booked successfully.");
        return true;
    }

    // Book a vacation
    public boolean bookVacation(LocalDate startDate, LocalDate endDate, Employee employee) {
        bookings.add(new Booking("Vacation", startDate, endDate, employee));
        System.out.println("Vacation booked successfully.");
        return true;
    }

    // Check availability for a room or employee at a specific date and time
    public boolean isAvailable(LocalDateTime dateTime, Room room, Employee employee) {
        for (Booking booking : bookings) {
            if (booking.getType().equals("Meeting") && booking.getMeetingDateTime().equals(dateTime) &&
                (booking.getRoom().equals(room) || booking.getEmployee().equals(employee))) {
                return false;
            }
        }
        return true;
    }

    // Print all bookings for a room or employee
    public void printAgenda(Object obj) {
        System.out.println("\nAgenda for " + (obj instanceof Room ? ((Room) obj).getName() : ((Employee) obj).getName()) + ":");
        boolean hasBookings = false;
        for (Booking booking : bookings) {
            if ((obj instanceof Room && booking.getRoom() == obj) ||
                (obj instanceof Employee && booking.getEmployee() == obj)) {
                System.out.println(booking);
                hasBookings = true;
            }
        }
        if (!hasBookings) {
            System.out.println("No bookings found.");
        }
    }
}
