package in.hemanth.trainingprojectsb4.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class DepartmentRepositorySQL{
//    @Modifying
//    @Query("delete from Department u where u.firstName = ?1")
//    public void deleteByRediskey(Integer rediskey);
//    @Query("select d from Department d where d.name = ?1")
//    public List<Department> getByName(String name);

    @Autowired
    EntityManager entityManager;

    public List<Department> getByName(String name) {
        String hql = "SELECT d FROM Department d WHERE d.name = :name";
        TypedQuery<Department> query = entityManager.createQuery(hql, Department.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
// The below one is used for different usecase
//    public void deleteByRediskey(Integer rediskey) {
//        String hql = "DELETE FROM Department d WHERE d.rediskey = :rediskey";
//        Query query = entityManager.createQuery(hql);
//        query.setParameter("rediskey", rediskey);
//        query.executeUpdate();
//    }

    public void deleteByRediskey(Integer rediskey) {
        String hql = "DELETE FROM Employee e WHERE e.rediskey = :rediskey";
        Query query = entityManager.createQuery(hql);
        query.setParameter("rediskey", rediskey);
        query.executeUpdate();
    }

}
