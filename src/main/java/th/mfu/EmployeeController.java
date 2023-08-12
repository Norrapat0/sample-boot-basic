package th.mfu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    // select all employeee
    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findByOrderByFnameAsc();
    }

    // select employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeById(@PathVariable long id) {
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        // check if id exists in db
        if (!optEmployee.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        Employee emp = optEmployee.get();
        return ResponseEntity.ok(emp);
    }

    // select employee by firstname
    @GetMapping("/employees/firstname/{firstname}")
    public ResponseEntity getEmployeeByFirstname(@PathVariable String firstname) {
        // get employee from db
        List<Employee> employees = employeeRepository.findByFnameStartingWith(firstname);
        // check if employee is empty
        if (employees.isEmpty()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        return ResponseEntity.ok(employees);
    }

    // create new employee
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        // TODO: check if id exists

        // add employee to repository
        employeeRepository.save(employee);

        // return created success message
        return ResponseEntity.ok("Employee created");
    }

    // //update employee
    @PutMapping("/employees/")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        // check if id not exists
        if (!employeeRepository.existsById(employee.getId())) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // update employee
        employeeRepository.save(employee);

        // return success message
        return ResponseEntity.ok("Employee updated");
    }

    //update employee with some fields using patch
    @PatchMapping("/employees/{id}")
    public ResponseEntity<String> patchEmployee(@PathVariable long id,
    @RequestBody EmployeeDTO employeeDTO){

        Optional<Employee> optEmployee=employeeRepository.findById(id);
        
        if(!optEmployee.isPresent())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
        Employee emp= optEmployee.get();

        employeeMapper.updateEmployeeFromDto(employeeDTO, emp);

        //save to db
        employeeRepository.save(emp);
        return ResponseEntity.ok("Employee Update");

    }

    // delete employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        // check if id not exists
        if (!employeeRepository.existsById(id)) {
            // return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // delete employee
        employeeRepository.deleteById(id);

        // return success message
        return ResponseEntity.ok("Employee deleted");
    }

}
