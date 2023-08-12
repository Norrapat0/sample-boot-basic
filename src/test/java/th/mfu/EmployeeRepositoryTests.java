package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {
    
    @Autowired
    private EmployeeRepository repository;

    // test query all
    @Test
    public void testGetAllEmployees() {
        // Act
        List<Employee> response = repository.findAll();
        // Assert
        assertEquals(10, response.size());
    }
}
