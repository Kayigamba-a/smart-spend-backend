package auca.example.smartspend.model;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
@Table(name="expense_type")
public class ExpenseType {
    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore 
    @ManyToMany(mappedBy="expenseTypes")
    private List<FinanceOfficer> financeOfficers = new ArrayList<>();

    public ExpenseType() {
    }

    public ExpenseType(String name, List<FinanceOfficer> financeOfficers) {
        this.name = name;
        this.financeOfficers = financeOfficers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FinanceOfficer> getFinanceOfficers() {
        return financeOfficers;
    }

    public void setFinanceOfficers(List<FinanceOfficer> financeOfficers) {
        this.financeOfficers = financeOfficers;
    }
    
    


}
