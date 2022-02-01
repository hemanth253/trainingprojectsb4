package in.hemanth.trainingprojectsb4.department;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    private Department department;

    @Before
    public void setUp() {
        department=new Department(1,"abc","ny");
    }

    @Test
    public void addRecord() {
        departmentService.addRecord(department);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    public void retrieveRecords() {
        List<Department> departments=new ArrayList<Department>();
        departments.add(department);
        when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> d = departmentService.retrieveRecords();
        assertEquals(departments, d);
    }


    @Test
    public void retrieveRecordsById() throws Exception {
        when(departmentRepository.isExists(1)).thenReturn(true);
        when(departmentRepository.findById(1)).thenReturn(department);
        Department d = departmentService.retrieveRecordsById(1);
        assertEquals(1, d.getRediskey());
    }

    @Test
    public void updateRecord() {
        departmentService.updateRecord(1,department);
        verify(departmentRepository, times(1)).updateRecord(1,department);
    }

    @Test
    public void deleteRecord() throws Exception{
        when(departmentRepository.isExists(1)).thenReturn(true);
        departmentService.deleteRecord(1);
        verify(departmentRepository, times(1)).deleteById(1);
    }
}