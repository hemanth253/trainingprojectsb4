package in.hemanth.trainingprojectsb4.employee;

import in.hemanth.trainingprojectsb4.department.DepartmentRepository;
import in.hemanth.trainingprojectsb4.exception.CustomIdNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmployeeService {

    private static Logger logger= LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private List<Employee> readEmployeeCSV(MultipartFile file) throws IOException, ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String record;
            while ((record = br.readLine()) != null) {
                String[] rs = record.split(",");
                if(!departmentRepository.isExists(Integer.parseInt(rs[4]))) {
                    throw new CustomIdNotFoundException("departmentId not found to ADD employee");
                }
//                Department d = departmentRepository.findById(Integer.parseInt(rs[4]));
//                System.out.println(d);
                Employee e=new Employee(rs[0], rs[1], Integer.parseInt(rs[2]), new SimpleDateFormat("dd/MM/yyyy").parse(rs[3]));
//                e.setDepartment(new Department(d));
//                e.setDepartment(d);
                e.setRediskey(Integer.parseInt(rs[4]));
                employees.add(e);
            }
        }
        return employees;
    }

    @Transactional
    public void addFileRecords(MultipartFile file) throws IOException, ParseException {
        List<Employee> employees = readEmployeeCSV(file);
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for(Employee e:employees){
            Runnable processor = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" : [RECEIVED] : "+ e.getName());
                    employeeRepository.save(e);
                    logger.info("LOGGER : Employee being saved : "+e);
                    System.out.println(Thread.currentThread().getName()+" : [PROCESSED] : "+ e.getName());
                }
            };
            executorService.execute(processor);
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){}
        long end = System.currentTimeMillis();
        logger.info("LOGGER : TheadPool execution done with time : {}",(end - start));
    }

    public void addRecord(Integer departmentId, Employee e) {
        if(!(departmentRepository.isExists(departmentId))){
            throw new CustomIdNotFoundException("departmentId not found to ADD employee");
        }
        e.setRediskey(departmentId);
        employeeRepository.save(e);
    }

    public List<Employee> retrieveRecords() {
        List<Employee> employees=employeeRepository.findAll();
        return employees;
    }

    // TODO //
    public Employee retrieveRecordsById(Integer employeeId) {
        boolean existsById = employeeRepository.existsById(employeeId);
        if(!existsById) throw new CustomIdNotFoundException("employeeId not found");
        return employeeRepository.findById(employeeId).get();
    }

    @Transactional
    public void updateRecord(Integer departmentId,Integer employeeId, Employee e) {
        if(!departmentRepository.isExists(departmentId)) {
            throw new CustomIdNotFoundException("departmentId not found to UPDATE employee");
        }
//        Department d= departmentRepository.findById(departmentId);
        boolean existsById = employeeRepository.existsById(employeeId);
        if(!existsById) throw new CustomIdNotFoundException("cant update, employeeId not found. Use post to insert employee");
        Employee employee = employeeRepository.getById(employeeId);
        employee.setName(e.getName());
        employee.setSalary(e.getSalary());
        employee.setDob(e.getDob());
        employee.setEmail(e.getEmail());
//        employee.setDepartment(d);
        employee.setRediskey(departmentId);
        employeeRepository.save(employee);
//        e.setDepartment(d);
//        employeeRepository.save(e);
    }

    public void deleteRecord(Integer employeeId) {
        boolean existsById = employeeRepository.existsById(employeeId);
        if(!existsById) throw new CustomIdNotFoundException("employeeId not found");
        employeeRepository.deleteById(employeeId);
    }
}
