package in.hemanth.trainingprojectsb4.department;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DepartmentService {

    private static final Logger logger= LogManager.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    private List<Department> readDepartmentCSV(MultipartFile file) throws IOException {
        List<Department> departments = new ArrayList<Department>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String record;
            while ((record = br.readLine()) != null) {
                String[] rs = record.split(",");
//                System.out.println(rs[0]);
//                System.out.println(rs[1]);
//                System.out.println(rs[2]);
                departments.add(new Department(Integer.parseInt(rs[0]), rs[1],rs[2]));
//                departments.add(new Department(rs[0],rs[1]));

            }
        }
        return departments;
    }

    public void addFileRecords(MultipartFile file) throws IOException {
        List<Department> departments = readDepartmentCSV(file);
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        for(Department d: departments){
            Runnable processor = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" : [RECEIVED] : "+ d.getName());
                    departmentRepository.save(d);
                    logger.info("LOGGER : Department being saved : "+d);
                    System.out.println(Thread.currentThread().getName()+" : [PROCESSED] : "+ d.getName());
                }
            };
            executorService.execute(processor);
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){}
        long end = System.currentTimeMillis();
        logger.info("LOGGER : ThreadPool execution done with time : {}",(end - start));
    }

    public void addRecord(Department d) {
//        System.out.println("Hi in DepartmentService");
        departmentRepository.save(d);
    }

    public List<Department> retrieveRecords() {
        return departmentRepository.findAll();
    }

    public Department retrieveRecordsById(Integer id) {
        if(!departmentRepository.isExists(id)) {
            throw new CustomIdNotFoundException("departmentId not found to RETRIEVE");
        }
        Department d = departmentRepository.findById(id);
        return d;
    }

    @Transactional
    public void updateRecord(Integer id, Department d) {
        departmentRepository.updateRecord(id,d);
    }

    public void deleteRecord(Integer id) {
        if(!departmentRepository.isExists(id)) {
            throw new CustomIdNotFoundException("Id not found to DELETE");
        }
        departmentRepository.deleteById(id);
    }
}