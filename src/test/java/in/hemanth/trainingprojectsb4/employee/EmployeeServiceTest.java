package in.hemanth.trainingprojectsb4.employee;

import in.hemanth.trainingprojectsb4.department.DepartmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    DepartmentRepository departmentRepository;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee=new Employee(1,"aa","a@a.com",11111,new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2005"),1);
    }

    @Test
    public void addRecord() {
        when(departmentRepository.isExists(1)).thenReturn(true);
        employeeService.addRecord(1,employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void retrieveRecords() {
        List<Employee> employees=new ArrayList<Employee>();
        employees.add(employee);
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> e = employeeService.retrieveRecords();
        assertEquals(employees, e);
    }

    @Test
    public void retrieveRecordsById() {
        when(employeeRepository.existsById(1)).thenReturn(true);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        Employee e = employeeService.retrieveRecordsById(1);
        assertEquals(1, e.getId());
    }

    @Test
    public void updateRecord() throws ParseException {
        Employee newEmployee=new Employee(null,"aa updated","a@a.com",11111,new SimpleDateFormat("dd/MM/yyyy").parse("08/01/2005"),1);
        when(departmentRepository.isExists(1)).thenReturn(true);
        when(employeeRepository.existsById(1)).thenReturn(true);
        when(employeeRepository.getById(1)).thenReturn(employee);
        employeeService.updateRecord(1,1,newEmployee);
        verify(employeeRepository, times(1)).save(Mockito.any(Employee.class));
    }

    @Test
    public void deleteRecord() {
        when(employeeRepository.existsById(1)).thenReturn(true);
        employeeService.deleteRecord(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }
}