package th.mfu;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeMgmtIT {
    private static Client client;

    private static String WEB_SERVICE_URI = "http://localhost:8080/employees/";

    @BeforeClass
    public static void createClient() {
        client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void closeClient() {
        client.close();
    }

    // test create
    @Test
    public void testCreate() {
        // Act
        Employee emp = new Employee();
        emp.setFname("Norrapat");
        emp.setLname("Saiiiii");
        emp.setBdate(new Date());
        emp.setSalary(15000);
        Account account = new Account();
        account.setUsername("gem");
        account.setPasssword("123456");
        emp.setAccount(account);

        // prepare invocation to the employee service
        Builder builder = client.target(WEB_SERVICE_URI).request().accept(MediaType.APPLICATION_JSON);
        // send the request
        Response response = builder.post(Entity.json(emp));

        int responseCode = response.getStatus();
        // Assert if response code is 200 OK
        assertEquals(Response.Status.OK.getStatusCode(), responseCode);
    }

    // test query all
    @Test
    public void testGetAllEmployees() {
        // Act
        Builder builder = client.target(WEB_SERVICE_URI).request().accept(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        int responseCode = response.getStatus();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), responseCode);

        // check if all employees are returned
        // List<Employee> employees = response.readEntity(new
        // GenericType<List<Employee>>() {});
        // assertEquals(10, employees.size());
    }

    // test delete
    @Test
    public void testDelete() {
        // create new employee named "dummy xxxxx"
        Employee emp = new Employee();
        emp.setFname("dummy");
        emp.setLname("xxxxx");
        emp.setBdate(new Date());
        emp.setSalary(90000);

        // invoke create service
        Builder builder = client.target(WEB_SERVICE_URI).request().accept(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.json(emp));

        int responseCode = response.getStatus();
        // Assert if response code is 200 OK
        assertEquals(Response.Status.OK.getStatusCode(), responseCode);

        // find employee by name
        builder = client.target(WEB_SERVICE_URI).path("firstname/dummy").request().accept(MediaType.APPLICATION_JSON);
        //call the service 
        response = builder.get();
        //get list of employee
        List<Employee> employees = response.readEntity(new GenericType<List<Employee>>() {});
        

        // Act
        builder = client.target(WEB_SERVICE_URI).path(""+employees.get(0).getId()).request().accept(MediaType.APPLICATION_JSON);
        response = builder.delete();
        responseCode = response.getStatus();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), responseCode);


        //find employee by name again
        builder = client.target(WEB_SERVICE_URI).path("firstname/dummy").request().accept(MediaType.APPLICATION_JSON);
        //call the service
        response = builder.get();
        // check if response is 
        
    }
    // test partial update

}
