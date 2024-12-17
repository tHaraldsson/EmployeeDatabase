package se.tommy.jFrame;

import se.tommy.workEntity.WorkRole;
import se.tommy.workEntity.WorkRoleDAO;
import se.tommy.workEntity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class InsertWindow{

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public InsertWindow() {
        JFrame insertFrame = new JFrame("INSERT");
        insertFrame.setSize(400, 300);
        insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        insertFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(15);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(15);

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryField = new JTextField(15);

        JLabel creationDateLabel = new JLabel("Creation date (yyyy-mm-dd):");
        JTextField creationDateField = new JTextField(15);

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(creationDateLabel);
        panel.add(creationDateField);
        panel.add(salaryLabel);
        panel.add(salaryField);

        // skapa och lägg till en knapp för att skicka data
        JButton submitButton = new JButton("SUBMIT");
        panel.add(new JLabel()); // Tom cell för layout
        panel.add(submitButton);

        // Lägg till panelen i fönstret
        insertFrame.add(panel);

        submitButton.addActionListener(e -> {
            try {
                // Hämta värden från textfälten
                String title = titleField.getText();
                String description = descriptionField.getText();
                String creationDateText = creationDateField.getText();
                String salaryText = salaryField.getText();

                // Konvertera och validera data
                Date creationDate = null;
                if (!creationDateText.isEmpty()) {
                    creationDate = Date.valueOf(creationDateText); // Förutsätter "YYYY-MM-DD"-format
                }
                double salary = Double.parseDouble(salaryText);

                WorkRole workRole = new WorkRole(title, description, salary, creationDate);

                workRoleDAO.insertWorkRole(workRole);

                JOptionPane.showMessageDialog(insertFrame, "Work role added successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(insertFrame, "Something went wrong");
            }

        });

        insertFrame.setVisible(true);
    }

}
