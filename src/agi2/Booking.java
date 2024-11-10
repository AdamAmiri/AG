package agi2;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private String type; // "Meeting" or "Vacation"
    private LocalDateTime meetingDateTime; // Only for meetings
    private LocalDate vacationStartDate; // Only for vacations
    private LocalDate vacationEndDate; // Only for vacations
    private Room room;
    private Employee employee;

    // Constructor for a meeting
    public Booking(String type, LocalDateTime meetingDateTime, Room room, Employee employee) {
        this.type = type;
        this.meetingDateTime = meetingDateTime;
        this.room = room;
        this.employee = employee;
    }

    // Constructor for a vacation
    public Booking(String type, LocalDate vacationStartDate, LocalDate vacationEndDate, Employee employee) {
        this.type = type;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getMeetingDateTime() {
        return meetingDateTime;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public LocalDate getVacationEndDate() {
        return vacationEndDate;
    }

    public Room getRoom() {
        return room;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String toString() {
        if (type.equals("Meeting")) {
            return "Meeting on " + meetingDateTime + " in " + room.getName() + " with " + employee.getName();
        } else {
            return "Vacation from " + vacationStartDate + " to " + vacationEndDate + " for " + employee.getName();
        }
    }
}
