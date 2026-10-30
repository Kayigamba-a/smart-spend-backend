package auca.example.smartspend.model;

import java.util.UUID;
import jakarta.persistence.*;


@Entity 
@Table(name= "cost_centre")
public class CostCentre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    int centreNumber;
    String name;
    public CostCentre() {
    }
    public CostCentre(int centreNumber, String name) {
        this.centreNumber = centreNumber;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getCentreNumber() {
        return centreNumber;
    }
    public void setCentreNumber(int centreNumber) {
        this.centreNumber = centreNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    

}
