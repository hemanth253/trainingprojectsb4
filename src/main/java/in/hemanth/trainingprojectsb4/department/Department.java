package in.hemanth.trainingprojectsb4.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@RedisHash("department")
@Builder
public class Department implements Serializable {

//    private static final long serialVersionUID = 1174448878147632579L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private Integer rediskey;
    private String name;
    private String location;

    public Department() {
    }

    public Department(Integer rediskey, String name, String location) {
        this.rediskey=rediskey;
        this.name = name;
        this.location = location;
    }

    public Department(Integer id, Integer rediskey, String name, String location) {
        this.id = id;
        this.rediskey = rediskey;
        this.name = name;
        this.location = location;
    }
}
