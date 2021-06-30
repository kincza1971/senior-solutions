package employees;

public class EmployeeNotFoundException extends IllegalArgumentException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
