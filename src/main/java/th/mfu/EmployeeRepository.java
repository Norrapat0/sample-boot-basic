package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public List<Employee> findAll();

    public List<Employee> findByFname(String firstname);

    public List<Employee> findByFnameStartingWith(String prefix);

    public List<Employee> findByOrderByFnameAsc();
}
