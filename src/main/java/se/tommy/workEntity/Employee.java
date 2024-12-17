package se.tommy.workEntity;

public class Employee {
    private Integer employeeId;
    private String name;
    private String email;
    private String password;
    private WorkRole workRole;

    public Employee(Integer employeeId, String name, String email, String password, WorkRole workRole) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }

    public Employee(String name, String email, String password, WorkRole workRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WorkRole getWorkRole() {
        return workRole;
    }

    public void setWorkRole(WorkRole workRole) {
        this.workRole = workRole;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
