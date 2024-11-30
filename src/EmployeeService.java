import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private Connection connection;

    public EmployeeService() {
        try{
           connection = DBConnection.connect();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void addEmployee(Employee employee) {
        String addEmployee = "INSERT INTO Employee (firstName, " +
                "lastName, " +
                "position, " +
                "department, " +
                "salary) VALUES (?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(addEmployee);) {
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getPosition());
            preparedStatement.setString(4,employee.getDepartment());
            preparedStatement.setDouble(5,employee.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Employee added successfully.");
            }else{
                System.out.println("Failed to add employee.");
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public Employee getEmployeeById(int employeeId){
        String getEmployeeById = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(getEmployeeById)){
            preparedStatement.setInt(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getInt("EmployeeID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Position"),
                        resultSet.getString("Department"),
                        resultSet.getDouble("Salary")
                );
                return employee;
            } else {
                System.out.println("Employee not found. Use a valid Employee ID.");
                return null;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
    public void updateEmployee(Employee employee, int employeeId) {
        String updateEmployeeSQL = "UPDATE Employee SET firstName = ?," +
                "lastName = ?," +
                "position = ?," +
                "department = ?," +
                "salary = ? " +
                "WHERE EmployeeID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeeSQL)){

            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getPosition());
            preparedStatement.setString(4,employee.getDepartment());
            preparedStatement.setDouble(5,employee.getSalary());
            preparedStatement.setInt(6,employeeId);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Employee updated successfully.");
            }else{
                System.out.println("Update employee failed. No such employee with ID: "+employeeId);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void deleteEmployee(int employeeId) {
        String deleteEmployeeSQL = "DELETE FROM Employee WHERE EmployeeID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployeeSQL)){

            preparedStatement.setInt(1, employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Employee deleted successfully.");
            }else{
                System.out.println("Delete employee failed. No such employee with ID: "+employeeId);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public List<Employee> getAllEmployees() {
        String getAllEmployees = "SELECT * FROM Employee";
        try(PreparedStatement preparedStatement = connection.prepareStatement(getAllEmployees);
            ResultSet resultSet = preparedStatement.executeQuery()
        ){
            while (resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getInt("EmployeeID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("position"),
                        resultSet.getString("department"),
                        resultSet.getDouble("salary")
                        );
                employeeList.add(employee);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return employeeList;
    }
}
