package auca.example.smartspend.model;

import java.util.UUID;
import jakarta.persistence.*;


@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(unique = true)
    private String staffNumber;


    private String email;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String staffNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffNumber = staffNumber;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    


}
