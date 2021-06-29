package employees;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    AtomicLong idGenerator = new AtomicLong();

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private List<Employee> employees = Collections.synchronizedList(
            new ArrayList<>(List.of(
                    new Employee(idGenerator.incrementAndGet(), "John Doe"),
                    new Employee(idGenerator.incrementAndGet(), "Jack Doe")
            ))
    );

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> filtered =employees.stream()
                .filter(employee -> prefix.isEmpty() ||
                        employee.getName()
                                .toLowerCase(Locale.ROOT)
                                .startsWith(prefix.get().toLowerCase(Locale.ROOT)))
                .toList();
            return modelMapper.map(filtered, targetListType);
    }

    public EmployeeDto findEmployeeById(Long id) {
        Employee found = employees.stream()
                .filter(employee1 -> employee1.getId()==id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find employee with this ID " +id));
        return modelMapper.map(found,EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee newEmployee = new Employee(idGenerator.incrementAndGet(),command.getName());
        employees.add(newEmployee);
        return modelMapper.map(newEmployee,EmployeeDto.class);
    }
}
