package se.tommy.jFrame;

import se.tommy.workEntity.WorkRole;
import se.tommy.workEntity.WorkRoleDAO;
import se.tommy.workEntity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;

public class ShowWorkRoleWindow {
    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public ShowWorkRoleWindow() {

        JFrame showWorkRoleFrame = new JFrame("SHOW WORK ROLE");
        showWorkRoleFrame.setSize(400, 300);
        showWorkRoleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        showWorkRoleFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JLabel workRoleIdLabel = new JLabel("Enter Work role ID:");
        JTextField workRoleIdField = new JTextField(15);

        panel.add(workRoleIdLabel);
        panel.add(workRoleIdField);

        JButton showButton = new JButton("SHOW");
        panel.add(new JLabel());
        panel.add(showButton);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(15);
        titleField.setEditable(false);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(15);
        descriptionField.setEditable(false);

        JLabel salaruLabel = new JLabel("Salary:");
        JTextField salaryField = new JTextField(15);
        salaryField.setEditable(false);

        JLabel creationDateLabel = new JLabel("Creation Date:");
        JTextField creationDateField = new JTextField(15);
        creationDateField.setEditable(false);

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(salaruLabel);
        panel.add(salaryField);
        panel.add(creationDateLabel);
        panel.add(creationDateField);

        showWorkRoleFrame.add(panel);

        showButton.addActionListener(e -> {
            try {
                String workRoleIdText = workRoleIdField.getText();
                if (workRoleIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(showWorkRoleFrame, "Please enter a work role ID");
                    return;
                }
                int workRoleId = Integer.parseInt(workRoleIdField.getText());

                WorkRole workRole = workRoleDAO.selectWorkRole(workRoleId);

                if (workRole != null) {
                    titleField.setText(workRole.getTitle());
                    descriptionField.setText(workRole.getDescription());
                    salaryField.setText(String.valueOf(workRole.getSalary()));
                    creationDateField.setText(workRole.getCreationDate().toString());
                } else {
                    JOptionPane.showMessageDialog(showWorkRoleFrame, "Work role not found");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(showWorkRoleFrame, "Error fetching work role" + ex.getMessage());
            }
        });
        showWorkRoleFrame.setVisible(true);
    }
}
