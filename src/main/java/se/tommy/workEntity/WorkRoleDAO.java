package se.tommy.workEntity;

import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDAO {

    public void insertWorkRole(WorkRole workRole) throws SQLException;

    public boolean updateWorkRole(WorkRole workRole) throws SQLException;

    public void deleteWorkRole(Integer roleId) throws SQLException;

    public WorkRole selectWorkRole(Integer roleId) throws SQLException;

    public List<WorkRole> selectAllWorkRoles() throws SQLException;
}
