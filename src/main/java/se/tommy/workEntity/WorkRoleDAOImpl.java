package se.tommy.workEntity;

import se.tommy.jdbc.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAOImpl implements WorkRoleDAO {

    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO work_role (title, description, salary, creation_date) VALUES (?, ?, ?, ?)";
            pStmt = conn.prepareStatement(sql);

            pStmt.setString(1, workRole.getTitle());
            pStmt.setString(2, workRole.getDescription());
            pStmt.setDouble(3, workRole.getSalary());
            if (workRole.getCreationDate() != null) {
                pStmt.setDate(4, workRole.getCreationDate());
            } else {
                pStmt.setNull(4, java.sql.Types.DATE);
            }

            int rowsAffected = pStmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closePrepareStatement(pStmt);
        }
    }

    @Override
    public boolean updateWorkRole(WorkRole workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean returnResult = false;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? WHERE role_id = ?";

            System.out.println("Executing SQL: " + sql);
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, workRole.getCreationDate());
            pstmt.setInt(5, workRole.getRoleId());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                conn.commit();
                returnResult = true;
                System.out.println("Work role successfully updated");
            } else {
                System.out.println("No rows affected, work role might not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closePrepareStatement(pstmt);
        }
        return returnResult;
    }

    @Override
    public void deleteWorkRole(Integer roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM work_role WHERE role_id = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, roleID);

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closePrepareStatement(pstmt);
        }
    }

    @Override
    public WorkRole selectWorkRole(Integer roleId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WorkRole workRole = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM work_role WHERE role_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roleId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                workRole = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closePrepareStatement(pstmt);
        }
        return workRole;
    }

    @Override
    public List<WorkRole> selectAllWorkRoles() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<WorkRole> workRoles = new ArrayList<WorkRole>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM work_role";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
               WorkRole workRole = new WorkRole(
                        rs.getInt("role_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );
                workRoles.add(workRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closePrepareStatement(pstmt);
        }
        return workRoles;
    }
}
