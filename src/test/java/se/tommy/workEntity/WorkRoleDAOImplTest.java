package se.tommy.workEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import se.tommy.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkRoleDAOImplTest {

    @AfterEach
    public void cleanUp() {
        System.out.println("Nu körs cleanup");
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS work_role");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeConnection(conn);
        }
        System.out.println("Nu är cleanup klar");
    }

    @Test
    void isThereWorkRole() {

            System.out.println("Nu startar testet");
            WorkRoleDAOImpl dao = new WorkRoleDAOImpl();
            WorkRole workRole = new WorkRole("Janitor", "I clean stuff", 10000, Date.valueOf("2020-01-01"));
            List<WorkRole> workRoles = new ArrayList<>();
            try {
                dao.insertWorkRole(workRole);
                workRoles = dao.selectAllWorkRoles();

                for (WorkRole workRole1 : workRoles) {
                    System.out.println(workRole1.getTitle());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            assertEquals(1, workRoles.size());
            System.out.println("Nu slutar testet");

    }
}