package in.hemanth.trainingprojectsb4.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DepartmentRepositorySQL departmentRepositorySQL;

    private static final String KEY="department";

    public boolean save(Department department){
//        System.out.println("Hi in DepartmentRepository");
        try{
            redisTemplate.opsForHash().put(KEY,department.getRediskey().toString(),department);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Department> findAll() {
        List<Department> departments=redisTemplate.opsForHash().values(KEY);
        return departments;
    }

    public boolean isExists(Integer departmentId){
        return redisTemplate.opsForHash().hasKey(KEY,departmentId.toString());
    }

    public Department findById(Integer departmentId) {
//        System.out.println(redisTemplate.opsForHash().get(KEY,departmentId.toString()));
        Department department=(Department) (redisTemplate.opsForHash().get(KEY,departmentId.toString()));
        return department;
    }

    public boolean updateRecord(Integer id, Department department) {
        try {
            redisTemplate.opsForHash().put(KEY, id.toString(), department);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(Integer id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            departmentRepositorySQL.deleteByRediskey(id);
//            System.out.println(departmentRepositorySQL.getByName("Business Development"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
