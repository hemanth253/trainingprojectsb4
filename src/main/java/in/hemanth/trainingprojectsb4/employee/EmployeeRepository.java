package in.hemanth.trainingprojectsb4.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    public List<Employee> findByDepartmentId(Integer departmentId);
}
