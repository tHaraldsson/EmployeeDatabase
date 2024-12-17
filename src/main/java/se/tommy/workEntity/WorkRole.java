package se.tommy.workEntity;

import java.sql.Date;

public class WorkRole {
    private Integer roleId;
    private String title;
    private String description;
    private double salary;
    private java.sql.Date creationDate;

    public WorkRole(int roleId, String title, String description, double salary, java.sql.Date creationDate) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }
    public WorkRole(String title, String description, double salary, java.sql.Date creationDate) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "WorkRole" +
                "roleId=" + roleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", creationDate=" + creationDate +
                "\n";
    }
}
