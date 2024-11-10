package agi2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarAppGUI {
    private CalendarApp app;
    private JFrame frame;
    private JTextArea outputArea;
    private JTextField roomNameField;
    private JTextField employeeNameField;
    private JTextField dateTimeField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JComboBox<String> roomComboBox;
    private JComboBox<String> employeeComboBox;
    private JPanel mainPanel;

    public CalendarAppGUI() {
        app = new CalendarApp();
        initialize();
    }

    private void initialize() {
        // Main Frame Setup
        frame = new JFrame("Calendar Application");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Sidebar for Navigation
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setPreferredSize(new Dimension(150, 500));

        JButton addRoomButton = new JButton("Add Room");
        addRoomButton.addActionListener(e -> showAddRoomPanel());

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(e -> showAddEmployeePanel());

        JButton bookMeetingButton = new JButton("Book Meeting");
        bookMeetingButton.addActionListener(e -> showBookMeetingPanel());

        JButton bookVacationButton = new JButton("Book Vacation");
        bookVacationButton.addActionListener(e -> showBookVacationPanel());

        JButton checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.addActionListener(e -> showCheckAvailabilityPanel());

        JButton printAgendaButton = new JButton("Print Agenda");
        printAgendaButton.addActionListener(e -> showPrintAgendaPanel());

        sidebar.add(addRoomButton);
        sidebar.add(addEmployeeButton);
        sidebar.add(bookMeetingButton);
        sidebar.add(bookVacationButton);
        sidebar.add(checkAvailabilityButton);
        sidebar.add(printAgendaButton);

        frame.add(sidebar, BorderLayout.WEST);

        // Main Panel for Action Forms
        mainPanel = new JPanel(new CardLayout());
        mainPanel.add(createAddRoomPanel(), "Add Room");
        mainPanel.add(createAddEmployeePanel(), "Add Employee");
        mainPanel.add(createBookMeetingPanel(), "Book Meeting");
        mainPanel.add(createBookVacationPanel(), "Book Vacation");
        mainPanel.add(createCheckAvailabilityPanel(), "Check Availability");
        mainPanel.add(createPrintAgendaPanel(), "Print Agenda");

        frame.add(mainPanel, BorderLayout.CENTER);

        // Output Area
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createAddRoomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        roomNameField = new JTextField();
        JButton addButton = new JButton("Add Room");
        addButton.addActionListener(this::addRoom);

        panel.add(new JLabel("Room Name:"));
        panel.add(roomNameField);
        panel.add(addButton);

        return panel;
    }

    private JPanel createAddEmployeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        employeeNameField = new JTextField();
        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(this::addEmployee);

        panel.add(new JLabel("Employee Name:"));
        panel.add(employeeNameField);
        panel.add(addButton);

        return panel;
    }

    private JPanel createBookMeetingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        dateTimeField = new JTextField();
        roomComboBox = new JComboBox<>();
        employeeComboBox = new JComboBox<>();
        JButton bookButton = new JButton("Book Meeting");
        bookButton.addActionListener(this::bookMeeting);

        panel.add(new JLabel("Meeting Date & Time (yyyy-MM-dd HH:mm):"));
        panel.add(dateTimeField);
        panel.add(new JLabel("Room:"));
        panel.add(roomComboBox);
        panel.add(new JLabel("Employee:"));
        panel.add(employeeComboBox);
        panel.add(bookButton);

        return panel;
    }

    private JPanel createBookVacationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        startDateField = new JTextField();
        endDateField = new JTextField();
        employeeComboBox = new JComboBox<>();
        JButton bookButton = new JButton("Book Vacation");
        bookButton.addActionListener(this::bookVacation);

        panel.add(new JLabel("Vacation Start Date (yyyy-MM-dd):"));
        panel.add(startDateField);
        panel.add(new JLabel("Vacation End Date (yyyy-MM-dd):"));
        panel.add(endDateField);
        panel.add(new JLabel("Employee:"));
        panel.add(employeeComboBox);
        panel.add(bookButton);

        return panel;
    }

    private JPanel createCheckAvailabilityPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        dateTimeField = new JTextField();
        roomComboBox = new JComboBox<>();
        employeeComboBox = new JComboBox<>();
        JButton checkButton = new JButton("Check Availability");
        checkButton.addActionListener(this::checkAvailability);

        panel.add(new JLabel("Date & Time (yyyy-MM-dd HH:mm):"));
        panel.add(dateTimeField);
        panel.add(new JLabel("Room:"));
        panel.add(roomComboBox);
        panel.add(new JLabel("Employee:"));
        panel.add(employeeComboBox);
        panel.add(checkButton);

        return panel;
    }

    private JPanel createPrintAgendaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton printButton = new JButton("Print Agenda");
        printButton.addActionListener(this::printAgenda);

        panel.add(printButton);
        return panel;
    }

    // Show Panels
    private void showAddRoomPanel() {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Add Room");
    }

    private void showAddEmployeePanel() {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Add Employee");
    }

    private void showBookMeetingPanel() {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Book Meeting");
    }

    private void showBookVacationPanel() {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Book Vacation");
    }

    private void showCheckAvailabilityPanel() {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Check Availability");
    }

    private void showPrintAgendaPanel() {
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, "Print Agenda");
    }

    // Action Handlers
    private void addRoom(ActionEvent e) {
        String roomName = roomNameField.getText().trim();
        if (!roomName.isEmpty()) {
            roomComboBox.addItem(roomName);
            outputArea.append("Room added: " + roomName + "\n");
            roomNameField.setText("");
        } else {
            outputArea.append("Room name cannot be empty.\n");
        }
    }

    private void addEmployee(ActionEvent e) {
        String employeeName = employeeNameField.getText().trim();
        if (!employeeName.isEmpty()) {
            employeeComboBox.addItem(employeeName);
            outputArea.append("Employee added: " + employeeName + "\n");
            employeeNameField.setText("");
        } else {
            outputArea.append("Employee name cannot be empty.\n");
        }
    }

    private void bookMeeting(ActionEvent e) {
        // Meeting booking logic
    }

    private void bookVacation(ActionEvent e) {
        // Vacation booking logic
    }

    private void checkAvailability(ActionEvent e) {
        // Availability checking logic
    }

    private void printAgenda(ActionEvent e) {
        // Agenda printing logic
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalendarAppGUI::new);
    }
}
