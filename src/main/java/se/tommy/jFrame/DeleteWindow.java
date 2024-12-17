package se.tommy.jFrame;

import se.tommy.workEntity.WorkRoleDAO;
import se.tommy.workEntity.WorkRoleDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeleteWindow{

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public DeleteWindow() {
        JFrame deleteFrame = new JFrame("DELETE WORK ROLE");
        deleteFrame.setSize(400, 300);
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        deleteFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel workRoleIdLabel = new JLabel("Work role ID to delete:");
        JTextField workRoleIdField = new JTextField(15);

        panel.add(workRoleIdLabel);
        panel.add(workRoleIdField);

        JButton deleteButton = new JButton("DELETE");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteFrame.add(panel);

        deleteButton.addActionListener(e -> {
            try {
                String workRoleIdText = workRoleIdField.getText();

                if (workRoleIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(deleteFrame, "Please enter a valid Work role ID");
                    return;
                }

                int workroleId = Integer.parseInt(workRoleIdText);

                workRoleDAO.deleteWorkRole(workroleId);

                JOptionPane.showMessageDialog(deleteFrame, "Work role deleted successfully");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(deleteFrame, "Please enter a valid number for Work role ID");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(deleteFrame, "Error deleting work role");
                ex.printStackTrace();
            }
        });
        deleteFrame.setVisible(true);
    }
}
