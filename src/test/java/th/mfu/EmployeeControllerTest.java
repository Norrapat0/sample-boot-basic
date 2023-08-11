package th.mfu;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    EmployeeController controller;

    // test query all
    @Test
    public void testGetAllEmployees() {
        // Act
        Collection<Employee> response = controller.getAllEmployees();
        // Assert
        assertEquals(10, response.size());
    }

    @Test
    public void testCreate() {
        Employee newemp = new Employee();
        newemp.setFname("David");
        newemp.setLname("Miller");
        newemp.setBdate(new Date());
        newemp.setSalary(100000);
        // Act
        ResponseEntity response = controller.createEmployee(newemp);
        // Assert check if response is ok
        assertEquals(200, response.getStatusCodeValue());

        ResponseEntity response2 = controller.getEmployeeByFirstname("David");
        // check if response2 returned with proper status code
        assertEquals(200, response2.getStatusCodeValue());

    }

    // test delete
    @Test
    public void testDelete() {

        // Act
        ResponseEntity response = controller.deleteEmployee(90);
        // Assert
        assertEquals(200, response.getStatusCodeValue());

        // try to check if employee is deleted
        ResponseEntity response2 = controller.getEmployeeById(90);
        // check if response2 returned with proper status code
        assertEquals(404, response2.getStatusCodeValue());
    }

    @Test
    public void testUpdate() {
        // get employee with id 91
        ResponseEntity response = controller.getEmployeeById(91);

        Employee emp = (Employee) response.getBody();

        emp.setFname("David");
        ResponseEntity response2 = controller.updateEmployee(emp);

        assertEquals(200, response2.getStatusCodeValue());
        ResponseEntity response3 = controller.getEmployeeById(91);
        // check if employee firstname is David
        assertEquals("David", ((Employee) response3.getBody()).getFname());
    }
}
