package se.tommy.jFrame;


import se.tommy.workEntity.Employee;
import se.tommy.workEntity.EmployeeDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LogInWindow {

    EmployeeDAO employeeDAO = new EmployeeDAO();

    public LogInWindow() {

        JFrame loginFrame = new JFrame("LOGIN");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));


        JLabel emailLabel = new JLabel("Enter email:", SwingConstants.CENTER);
        JTextField emailField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Enter password:", SwingConstants.CENTER);
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");


        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);


        loginFrame.add(loginPanel);

        loginButton.addActionListener(e -> {

            try {
                String email = emailField.getText();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter a valid email");
                    return;
                }

                String password = String.valueOf(passwordField.getPassword());
                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame, "Please enter a valid password");
                    return;
                }

                Employee employee = employeeDAO.logIn(email, password);

                if (employee != null && employee.getEmail().equals(email) && employee.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful\nWelcome: " + employee.getName());

                    showEmployeeInfo(employee);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Login Failed");

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(loginFrame, "Error fetching employee" + ex.getMessage());
            }

        });


        loginFrame.setVisible(true);

    }

    private void showEmployeeInfo(Employee employee) {

        JFrame employeeInfoFrame = new JFrame("Employee Info");
        employeeInfoFrame.setSize(400, 300);
        employeeInfoFrame.setLocationRelativeTo(null);

        JPanel infoPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel idLabel = new JLabel("Employee ID: " + employee.getEmployeeId());
        JLabel roleIdLabel = new JLabel("Role ID: " + employee.getWorkRole().getRoleId());
        JLabel nameLabel = new JLabel("Name: " + employee.getName());
        JLabel titleLabel = new JLabel("Work role title: " + employee.getWorkRole().getTitle());
        JLabel descriptionLabel = new JLabel("Description: " + employee.getWorkRole().getDescription());
        JLabel salaryLabel = new JLabel("Salary: " + employee.getWorkRole().getSalary());
        JLabel creationDateLabel = new JLabel("Creation date: " + employee.getWorkRole().getCreationDate());

        infoPanel.add(idLabel);
        infoPanel.add(roleIdLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(titleLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(salaryLabel);
        infoPanel.add(creationDateLabel);

        employeeInfoFrame.add(infoPanel);
        employeeInfoFrame.setVisible(true);
    }
}
