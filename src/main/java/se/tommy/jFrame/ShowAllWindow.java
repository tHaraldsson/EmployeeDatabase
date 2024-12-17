package se.tommy.jFrame;

import se.tommy.workEntity.WorkRole;
import se.tommy.workEntity.WorkRoleDAO;
import se.tommy.workEntity.WorkRoleDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ShowAllWindow {

    WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();

    public ShowAllWindow() {
        JFrame showAllFrame = new JFrame("SHOW ALL PERSONS");
        showAllFrame.setSize(600, 400);
        showAllFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        showAllFrame.setLocationRelativeTo(null);


        try {
            List<WorkRole> workRoles = workRoleDAO.selectAllWorkRoles();

            String[] columnNames = {"Work role Id", "Title", "Description", "Salary", "Creation date"};

            Object[][] rowData = new Object[workRoles.size()][5];

            for (int i = 0; i < workRoles.size(); i++) {
                WorkRole workRole = workRoles.get(i);
                rowData[i][0] = workRole.getRoleId();
                rowData[i][1] = workRole.getTitle();
                rowData[i][2] = workRole.getDescription();
                rowData[i][3] = workRole.getSalary();
                rowData[i][4] = workRole.getCreationDate().toString();
            }

            DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
            JTable table = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(table);
            showAllFrame.add(scrollPane);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(showAllFrame, "Error fetching persons from database");
            return;
        }
        showAllFrame.setVisible(true);
    }
}
