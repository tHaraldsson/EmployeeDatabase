package se.tommy.workEntity;


import se.tommy.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {


    public Employee logIn(String email, String password) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WorkRole workRole = null;
        Employee employee = null;
        try {
            conn = JDBCUtil.getConnection();

            // SQL fråga som hämtar både employee och work role tabellen ifall email och lösenord som kommer in finns
            String sql = """
                    SELECT *
                    from employee e
                    left join work_role w on e.role_id = w.role_id
                    where e.email = ? and e.password = ?
                    """;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            // Hämtar data och lägger in i nytt objekt
            rs = pstmt.executeQuery();
            while (rs.next()) {
                workRole = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );
                // Här läggs även workRole objektet in i employees objekt som en attribut
                employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        workRole
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closePrepareStatement(pstmt);
        }
        return employee;
    }
}
