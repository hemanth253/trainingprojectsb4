package in.hemanth.trainingprojectsb4.employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    private static Logger logger= LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value="/employee",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public HashMap<String, String> addFileRecords(@RequestParam MultipartFile[] files) throws IOException, ParseException {
        for(MultipartFile file:files){
            employeeService.addFileRecords(file);
        }
        logger.info("LOGGER : Added all records in files");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Added all records in files");
        return map;
    }

    @PostMapping(value = "/department/{departmentId}/employee")
    public HashMap<String, String> addRecord(@RequestBody Employee e, @PathVariable Integer departmentId){
        employeeService.addRecord(departmentId,e);
        logger.info("LOGGER : Added a record");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Added record");
        return map;
    }

    @GetMapping(value = "/employee")
    public List<Employee> retrieveRecords(){
        List<Employee> employees= employeeService.retrieveRecords();
        logger.info("LOGGER : Retrieved all records");
        return employees;
    }

    @GetMapping(value = "/employee/{employeeId}")
    public Employee retrieveRecordById(@PathVariable Integer employeeId){
        Employee employee = employeeService.retrieveRecordsById(employeeId);
        logger.info("LOGGER : Retrieved a record");
        return employee;
    }

    @PutMapping(value = "/department/{departmentId}/employee/{employeeId}")
    public HashMap<String, String> updateRecord(@RequestBody Employee e, @PathVariable Integer departmentId, @PathVariable Integer employeeId){
        employeeService.updateRecord(departmentId,employeeId,e);
        logger.info("LOGGER : Updated a record");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Updated record");
        return map;
    }

    @DeleteMapping(value = "/employee/{employeeId}")
    public HashMap<String, String> deleteRecord(@PathVariable Integer employeeId){
        employeeService.deleteRecord(employeeId);
        logger.info("LOGGER : Deleted a record");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Deleted record");
        return map;
    }

}
