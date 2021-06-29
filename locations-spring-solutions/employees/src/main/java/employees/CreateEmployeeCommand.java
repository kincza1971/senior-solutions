package employees;


import lombok.Data;

@Data
public class CreateEmployeeCommand {

    private Long id;
    private String name;
}
