import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();
    private Scanner scanner = new Scanner(System.in);
    public void displayMenu() {
        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee by ID");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. List All Employees");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    listEmployees();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Enter a valid choice.");
            }
        }
    }

    private void addEmployee() {
        System.out.println("Enter employee details:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Position: ");
        String position = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Employee employee = new Employee(0, firstName, lastName, position, department, salary);
        employeeService.addEmployee(employee);
    }

    private void viewEmployee(){
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        Employee employee = employeeService.getEmployeeById(employeeId);
        if(employee != null){
            System.out.println(employee);
        }else {
            System.out.println("Employee not found.");
        }
    }
    private void updateEmployee(){
        System.out.println("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);
        if(existingEmployee == null){
            System.out.println("Employee not found. Please enter a valid employee Id");
            return;
        }
        System.out.println("Enter new details for the employee (leave blank to keep current values):");
        System.out.print("First Name [" + existingEmployee.getFirstName() + "]: ");
        String firstName = scanner.nextLine();
        if (firstName.isEmpty()) {
            firstName = existingEmployee.getFirstName();
        }

        System.out.print("Last Name [" + existingEmployee.getLastName() + "]: ");
        String lastName = scanner.nextLine();
        if (lastName.isEmpty()) {
            lastName = existingEmployee.getLastName();
        }

        System.out.print("Position [" + existingEmployee.getPosition() + "]: ");
        String position = scanner.nextLine();
        if (position.isEmpty()) {
            position = existingEmployee.getPosition();
        }

        System.out.print("Department [" + existingEmployee.getDepartment() + "]: ");
        String department = scanner.nextLine();
        if (department.isEmpty()) {
            department = existingEmployee.getDepartment();
        }

        System.out.print("Salary [" + existingEmployee.getSalary() + "]: ");
        String salaryInput = scanner.nextLine();
        double salary = salaryInput.isEmpty() ? existingEmployee.getSalary() : Double.parseDouble(salaryInput);

        Employee updatedEmployee = new Employee(employeeId, firstName, lastName, position, department, salary);
        employeeService.updateEmployee(updatedEmployee,employeeId);
    }
    private void deleteEmployee(){
        System.out.println("Enter employee ID: ");
        int employeeId = scanner.nextInt();

        Employee employeeToDelete = employeeService.getEmployeeById(employeeId);
        if(employeeToDelete == null){
            System.out.println("Invalid employee Id.");
            return;
        }
        employeeService.deleteEmployee(employeeId);
    }
    private void listEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        for(Employee employee : employeeList){
            System.out.println(employee);
        }
    }
}
