package th.mfu;

import java.util.Date;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date bdate;
    private long salary;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
