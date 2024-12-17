package se.tommy.jFrame;

import se.tommy.workEntity.WorkRole;
import se.tommy.workEntity.WorkRoleDAO;
import se.tommy.workEntity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class UpdateWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public UpdateWindow() {

        JFrame updateFrame = new JFrame("UPDATE WORK ROLE");
        updateFrame.setSize(400, 300);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        updateFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel workRoleIdLabel = new JLabel("Enter Work role ID:");
        JTextField workRoleIdField = new JTextField(15);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(15);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(15);

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryField = new JTextField(15);

        JLabel creationDateLabel = new JLabel("Creation date:");
        JTextField creationDateField = new JTextField(15);

        panel.add(workRoleIdLabel);
        panel.add(workRoleIdField);
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(creationDateLabel);
        panel.add(creationDateField);

        JButton fetchButton = new JButton("FETCH");
        JButton updateButton = new JButton("UPDATE");
        panel.add(fetchButton);
        panel.add(updateButton);

        updateFrame.add(panel);

        fetchButton.addActionListener(e -> {

            try {
                String workRoleIdText = workRoleIdField.getText();
                if (workRoleIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(updateFrame, "Please enter Work Role ID");
                    return;
                }
                int workRoleId = Integer.parseInt(workRoleIdField.getText());
                WorkRole workRole = workRoleDAO.selectWorkRole(workRoleId);
                if (workRole == null) {
                    JOptionPane.showMessageDialog(updateFrame, "Work role NOT found");
                    return;
                }
                titleField.setText(workRole.getTitle());
                descriptionField.setText(workRole.getDescription());
                salaryField.setText(String.valueOf(workRole.getSalary()));
                creationDateField.setText(workRole.getCreationDate() != null ? workRole.getCreationDate().toString() : "");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateFrame, "Error fetching work role" + ex.getMessage());
            }
        });

        updateButton.addActionListener(e -> {
            try {
                // Hämtar info från textrutorna och säkerställer att inga rutor är tomma innan man går vidare

                String workRoleIdText = workRoleIdField.getText();
                if (workRoleIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(updateFrame, "Please enter Work Role ID");
                    return;
                }
                int workRoleId = Integer.parseInt(workRoleIdField.getText());

                String title = titleField.getText();
                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(updateFrame, "Title cannot be empty");
                    return;
                }
                String description = descriptionField.getText();
                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(updateFrame, "Description cannot be empty");
                    return;
                }
                String salaryText = salaryField.getText();
                if (salaryText.isEmpty()) {
                    JOptionPane.showMessageDialog(updateFrame, "Salary cannot be empty");
                    return;
                }
                double salary = Double.parseDouble(salaryText);

                String creationDateText = creationDateField.getText();
                if (creationDateText.isEmpty()) {
                    JOptionPane.showMessageDialog(updateFrame, "Creation date cannot be empty");
                    return;
                }
                Date creationDate = Date.valueOf(creationDateText);


                WorkRole updatedworkRole = new WorkRole(workRoleId, title, description, salary, creationDate);
                boolean updateResult = workRoleDAO.updateWorkRole(updatedworkRole);

                if (updateResult) {
                    JOptionPane.showMessageDialog(updateFrame, "Work role updated successfully");
                } else {
                    JOptionPane.showMessageDialog(updateFrame, "Work role update failed");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(updateFrame, "Error updating work role" + ex.getMessage());
            }
        });
        updateFrame.setVisible(true);
    }
}

