package test;


import org.junit.jupiter.api.Test;

import agi2.CalendarApp;
import agi2.Employee;
import agi2.Room;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class BookingTest {

    @Test
    public void testBookingMeetingSuccess() {
        // Arrange
        Room room = new Room("Conference Room");
        Employee employee = new Employee("John Doe");
        CalendarApp app = new CalendarApp();
        LocalDateTime meetingTime = LocalDateTime.of(2024, 11, 10, 10, 0);

        // Act
        boolean result = app.bookMeeting(meetingTime, room, employee);

        // Assert
        assertTrue(result, "The meeting should be booked successfully.");
    }

    @Test
    public void testBookingMeetingConflict() {
        // Arrange
        Room room = new Room("Conference Room");
        Employee employee = new Employee("John Doe");
        CalendarApp app = new CalendarApp();
        LocalDateTime meetingTime = LocalDateTime.of(2024, 11, 10, 10, 0);
        app.bookMeeting(meetingTime, room, employee); // First booking

        // Act
        boolean result = app.bookMeeting(meetingTime, room, employee); // Trying to book the same time again

        // Assert
        assertFalse(result, "The meeting should not be booked due to conflict.");
    }

    @Test
    public void testBookingVacationSuccess() {
        // Arrange
        Employee employee = new Employee("Jane Doe");
        CalendarApp app = new CalendarApp();
        LocalDate startDate = LocalDate.of(2024, 11, 10);
        LocalDate endDate = LocalDate.of(2024, 11, 14);

        // Act
        boolean result = app.bookVacation(startDate, endDate, employee);

        // Assert
        assertTrue(result, "The vacation should be booked successfully.");
    }

    @Test
    public void testBookingVacationOverlap() {
        // Arrange
        Employee employee = new Employee("Jane Doe");
        CalendarApp app = new CalendarApp();
        LocalDate startDate1 = LocalDate.of(2024, 11, 10);
        LocalDate endDate1 = LocalDate.of(2024, 11, 14);
        app.bookVacation(startDate1, endDate1, employee); // First vacation booking

        LocalDate startDate2 = LocalDate.of(2024, 11, 12);
        LocalDate endDate2 = LocalDate.of(2024, 11, 16);

        // Act
        boolean result = app.bookVacation(startDate2, endDate2, employee); // Overlapping vacation

        // Assert
        assertTrue(result, "The vacation should not be booked due to overlap.");
    }
}
