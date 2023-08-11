package th.mfu;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("first_name")
    private String fname;
    @JsonProperty("last_name")
    private String lname;

    private Date bdate;
    private long salary;

    public Employee() {

    }

    public Employee(long id, String fname, String lname, Date bdate, long salary) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getBdate() {
        return bdate;
    }

    public long getSalary() {
        return salary;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

}
