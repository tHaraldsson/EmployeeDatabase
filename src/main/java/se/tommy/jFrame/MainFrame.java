package se.tommy.jFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Employee Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));

        // Skapa knapparna
        JButton insertButton = new JButton("INSERT");
        JButton deleteButton = new JButton("DELETE");
        JButton showAllButton = new JButton("SHOW ALL WORK ROLES");
        JButton showPersonButton = new JButton("SHOW WORK ROLE");
        JButton updateButton = new JButton("UPDATE");
        JButton logInButton = new JButton("LOG IN");


        // Lägg till knapparna
        add(insertButton);
        add(deleteButton);
        add(showAllButton);
        add(showPersonButton);
        add(updateButton);
        add(logInButton);

        // Lägg till actions för varje knapp
        insertButton.addActionListener(e -> new InsertWindow());
        deleteButton.addActionListener(e -> new DeleteWindow());
        showAllButton.addActionListener(e -> new ShowAllWindow());
        showPersonButton.addActionListener(e -> new ShowWorkRoleWindow());
        updateButton.addActionListener(e -> new UpdateWindow());
        logInButton.addActionListener(e -> new LogInWindow());

        setLocationRelativeTo(null);
    }
}
