import javax.swing.*;
import java.awt.*;

public class CarRentalGUI {
    private CarRentalSystem rentalSystem;

    public CarRentalGUI(CarRentalSystem system) {
        this.rentalSystem = system;
        createGUI();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Car Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLayout(new BorderLayout());

        // ===== Modern Look Styling =====
        frame.getContentPane().setBackground(new Color(23, 23, 23)); // blacky background

        // Title
        JLabel title = new JLabel("CAR RENTAL SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(255, 255, 255)); // change color to white
        frame.add(title, BorderLayout.NORTH);

        // Buttons
        JPanel panel = new JPanel();
        panel.setBackground(new Color(24, 24, 24)); // Match background
        JButton rentButton = new JButton("Rent a Car");
        JButton returnButton = new JButton("Return a Car");
        JButton exitButton = new JButton("Exit");

        // Apply modern button colors
        rentButton.setBackground(new Color(40, 167, 69));   // Green
        rentButton.setForeground(Color.WHITE);
        returnButton.setBackground(new Color(247, 239, 10)); // yellow
        returnButton.setForeground(Color.BLACK);
        exitButton.setBackground(new Color(220, 53, 69)); // red
        exitButton.setForeground(Color.WHITE);

        // Make buttons a little larger
        Dimension btnSize = new Dimension(120, 35);
        rentButton.setPreferredSize(btnSize);
        returnButton.setPreferredSize(btnSize);
        exitButton.setPreferredSize(btnSize);

        panel.add(rentButton);
        panel.add(returnButton);
        panel.add(exitButton);
        frame.add(panel, BorderLayout.CENTER);

        // Actions
        rentButton.addActionListener(e -> rentCar());
        returnButton.addActionListener(e -> returnCar());
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void rentCar() {
        String customerName = JOptionPane.showInputDialog("Enter your name:");
        if (customerName == null || customerName.trim().isEmpty()) return;

        // Show available cars
        StringBuilder carList = new StringBuilder("Available Cars:\n");
        for (Car car : rentalSystem.getCars()) {
            if (car.isOnline()) {
                carList.append(car.getCarID()).append(" - ")
                       .append(car.getBrand()).append(" ")
                       .append(car.getModel()).append("\n");
            }
        }

        if (carList.toString().equals("Available Cars:\n")) {
            JOptionPane.showMessageDialog(null, "No cars available!");
            return;
        }

        String carID = JOptionPane.showInputDialog(carList.toString() + "\nEnter Car ID:");
        if (carID == null || carID.trim().isEmpty()) return;

        String daysStr = JOptionPane.showInputDialog("Enter number of days:");
        if (daysStr == null || daysStr.trim().isEmpty()) return;

        int days;
        try {
            days = Integer.parseInt(daysStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid number of days!");
            return;
        }

        Customer customer = new Customer("CUS" + (rentalSystem.getCustomers().size() + 1), customerName);
        rentalSystem.addCustomer(customer);

        Car selectedCar = null;
        for (Car car : rentalSystem.getCars()) {
            if (car.getCarID().equalsIgnoreCase(carID.trim()) && car.isOnline()) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar != null) {
            double totalPrice = selectedCar.calculatePrice(days);

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Customer: " + customer.getname() +
                    "\nCar: " + selectedCar.getBrand() + " " + selectedCar.getModel() +
                    "\nDays: " + days +
                    "\nTotal: $" + totalPrice +
                    "\nConfirm rental?");
            if (confirm == JOptionPane.YES_OPTION) {
                rentalSystem.rentCar(selectedCar, customer, days);
                JOptionPane.showMessageDialog(null, "Car rented successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid car selection or car not available.");
        }
    }

    private void returnCar() {
        String carID = JOptionPane.showInputDialog("Enter Car ID to return:");
        if (carID == null || carID.trim().isEmpty()) return;

        Car carToReturn = null;
        for (Car car : rentalSystem.getCars()) {
            if (car.getCarID().equalsIgnoreCase(carID.trim()) && !car.isOnline()) {
                carToReturn = car;
                break;
            }
        }

        if (carToReturn != null) {
            rentalSystem.returncar(carToReturn);
            JOptionPane.showMessageDialog(null, "Car returned successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid car ID or car is not rented.");
        }
    }
}
