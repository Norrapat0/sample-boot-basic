package th.mfu;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {
    
    private Long id;

    @JsonProperty("first_name")
    private String fname;
    
    @JsonProperty("last_name")
    private String lname;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date birthday;

    private long salary;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String firstname) {
        this.fname = firstname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lastname) {
        this.lname = lastname;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }

    

}
