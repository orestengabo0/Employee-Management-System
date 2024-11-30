public class Main {
    public static void main(String[] args) {
        Tables table = new Tables();
        table.createEmployeeTable();
        EmployeeController employeeController = new EmployeeController();
        employeeController.displayMenu();
    }
}