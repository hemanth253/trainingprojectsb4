package in.hemanth.trainingprojectsb4.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private Integer salary;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dob;
    private Integer rediskey;

    public Employee() {
    }

    public Employee(String name, String email, Integer salary, Date dob) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.dob = dob;
    }

    public Employee(String name, String email, Integer salary, Date dob, Integer rediskey) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.dob = dob;
        this.rediskey=rediskey;
    }

    public Employee(Integer id, String name, String email, Integer salary, Date dob, Integer rediskey) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.dob = dob;
        this.rediskey = rediskey;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", dob=" + dob +
                ", rediskey=" + rediskey +
                '}';
    }

}
