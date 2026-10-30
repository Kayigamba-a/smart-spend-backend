package auca.example.smartspend.model;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity 
@Table(name="finance_officer")
public class FinanceOfficer {

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String email;

    @ManyToOne
    @JoinColumn(name="cost_centre_id")
    private CostCentre costCentre;

    @ManyToMany
    @JoinTable(
        name="finance_officer_expense_type",
        joinColumns = @JoinColumn(name="finance_officer_id"),
        inverseJoinColumns = @JoinColumn(name="expense_type_id")
    )
    private List<ExpenseType> expenseTypes = new ArrayList<>();

    public FinanceOfficer() {
    }

    public FinanceOfficer(String firstName, String lastName, String email, CostCentre costCentre,
            List<ExpenseType> expenseTypes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.costCentre = costCentre;
        this.expenseTypes = expenseTypes;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CostCentre getCostCentre() {
        return costCentre;
    }

    public void setCostCentre(CostCentre costCentre) {
        this.costCentre = costCentre;
    }

    public List<ExpenseType> getExpenseTypes() {
        return expenseTypes;
    }

    public void setExpenseTypes(List<ExpenseType> expenseTypes) {
        this.expenseTypes = expenseTypes;
    }
    
    

    

}
