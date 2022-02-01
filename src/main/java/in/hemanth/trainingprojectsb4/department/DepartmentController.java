package in.hemanth.trainingprojectsb4.department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class DepartmentController {

    private static Logger logger= LogManager.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value="/department",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public HashMap<String, String> addFileRecords(@RequestParam MultipartFile[] files) throws IOException {
        for(MultipartFile file:files){
            departmentService.addFileRecords(file);
        }
        logger.info("LOGGER : Added all records in files");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Added all records in files");
        return map;
    }

    @PostMapping(value = "/department")
    public HashMap<String, String> addRecord(@RequestBody Department d){
        departmentService.addRecord(d);
        logger.info("LOGGER : Added a record");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Added record");
        return map;
    }

    @GetMapping(value = "/department")
    public List<Department> retrieveRecords(){
        List<Department> departments = departmentService.retrieveRecords();
        logger.info("LOGGER : Retrieved all records");
        return departments;
    }

    @GetMapping("/department/{id}")
    public Department retrieveRecordById(@PathVariable Integer id){
        Department department = departmentService.retrieveRecordsById(id);
        logger.info("LOGGER : Retrieved a record");
        return department;
    }

    @PutMapping("/department/{id}")
    public HashMap<String, String> updateRecord(@RequestBody Department d, @PathVariable Integer id){
        departmentService.updateRecord(id,d);
        logger.info("LOGGER : Updated a record");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Updated record");
        return map;
    }

    @DeleteMapping("/department/{id}")
    public HashMap<String, String> deleteRecord(@PathVariable Integer id){
        departmentService.deleteRecord(id);
        logger.info("LOGGER : Deleted a record");
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Deleted record");
        return map;
    }

}


//@RestController
//public class DepartmentController {
//
//    private static Logger logger= LogManager.getLogger(DepartmentController.class);
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    @PostMapping(value="/department",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public HashMap<String, String> addFileRecords(@RequestParam MultipartFile[] files) throws IOException, ParseException {
//        for(MultipartFile file:files){
//            departmentService.addFileRecords(file);
//        }
//        logger.info("LOGGER : Added all records in files");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("msg", "Added all records in files");
//        return map;
//    }
//
//    @PostMapping(value = "/department")
//    public HashMap<String, String> addRecord(@RequestBody Department d){
//        departmentService.addRecord(d);
//        logger.info("LOGGER : Added a record");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("msg", "Added record");
//        return map;
//    }
//
//    @GetMapping(value = "/department")
//    public List<Department> retrieveRecords(){
//        List<Department> departments = departmentService.retrieveRecords();
//        logger.info("LOGGER : Retrieved all records");
//        return departments;
//    }
//
//    @GetMapping("/department/{id}")
//    public Department retrieveRecordById(@PathVariable Integer id){
//        Department department = departmentService.retrieveRecordsById(id);
//        logger.info("LOGGER : Retrieved a record");
//        return department;
//    }
//
//    @PutMapping("/department/{id}")
//    public HashMap<String, String> updateRecord(@RequestBody Department d, @PathVariable Integer id){
//        departmentService.updateRecord(id,d);
//        logger.info("LOGGER : Updated a record");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("msg", "Updated record");
//        return map;
//    }
//
//    @DeleteMapping("/department/{id}")
//    public HashMap<String, String> deleteRecord(@PathVariable Integer id){
//        departmentService.deleteRecord(id);
//        logger.info("LOGGER : Deleted a record");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("msg", "Deleted record");
//        return map;
//    }
//
//}
